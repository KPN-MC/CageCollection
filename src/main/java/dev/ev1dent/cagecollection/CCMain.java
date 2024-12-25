package dev.ev1dent.cagecollection;

import dev.ev1dent.cagecollection.Commands.CommandCage;
import dev.ev1dent.cagecollection.events.NoSpawnEggs;
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
        this.getCommand("cage").setExecutor(new CommandCage(this));
        addTabCompletion();
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new SpawnerBreakEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new SpawnerPlaceEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new NoSpawnEggs(), this);
    }


    public void addTabCompletion() {

    }
}
