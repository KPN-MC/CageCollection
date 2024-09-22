package dev.ev1dent.cageCollection;

import org.bukkit.plugin.java.JavaPlugin;

public final class CCMain extends JavaPlugin {

    public CCMain plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands(){

    }

    public void registerListeners(){

    }


    public void addTabCompletion() {

    }
}
