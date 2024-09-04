package si.f5.actedsauce.latestlogs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LogCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("log"))) return false;
        if(!(commandSender.hasPermission("latestlogs.commands.log"))) {
            commandSender.sendMessage("You don't have permission enough to execute this command!");
            return false;
        }
        File[] listFiles = Bukkit.getServer().getWorldContainer().listFiles();
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(listFiles));
        for (File file:files) {
            if(!(file.getName().equals("logs"))) continue;
            ArrayList<File> logs = new ArrayList<File>(Arrays.asList(file.listFiles()));
            for(File log:logs) {
                if(!(log.getName().equals("latest.log"))) continue;
                try(RandomAccessFile randomAccessFile = new RandomAccessFile(log,"r")) {
                    long length = randomAccessFile.length();
                    if(length<5000) {
                        randomAccessFile.seek(0);
                    } else {
                        randomAccessFile.seek(length-5000);
                    }
                    String data;
                    while ((data=randomAccessFile.readLine())!=null) {
                        commandSender.sendMessage(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
