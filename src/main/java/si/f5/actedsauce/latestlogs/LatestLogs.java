package si.f5.actedsauce.latestlogs;

import org.bukkit.plugin.java.JavaPlugin;

public final class LatestLogs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("log").setExecutor(new LogCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
