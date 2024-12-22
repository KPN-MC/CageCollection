package dev.ev1dent.cageCollection;

import dev.ev1dent.cageCollection.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CCMain extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
    }
    public void onDisable() {
    }

    public void registerCommands(){
        addTabCompletion();
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new SpawnerBreakEvent(this), this);
    }


    public void addTabCompletion() {

    }
}
