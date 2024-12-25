package dev.ev1dent.cagecollection;

import dev.ev1dent.cagecollection.events.SpawnerBreakEvent;
import dev.ev1dent.cagecollection.events.SpawnerPlaceEvent;
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
        Bukkit.getPluginManager().registerEvents(new SpawnerPlaceEvent(this), this);
    }


    public void addTabCompletion() {

    }
}
