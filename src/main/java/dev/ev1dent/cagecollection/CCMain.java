package dev.ev1dent.cagecollection;

import dev.ev1dent.cagecollection.Commands.CommandCage;
import dev.ev1dent.cagecollection.Commands.tabCompletion.CageTC;
import dev.ev1dent.cagecollection.events.NoAnvilRename;
import dev.ev1dent.cagecollection.events.NoSpawnEggs;
import dev.ev1dent.cagecollection.events.SpawnerBreakEvent;
import dev.ev1dent.cagecollection.events.SpawnerPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CCMain extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
    }
    public void onDisable() {
    }

    public void registerCommands(){
        Objects.requireNonNull(this.getCommand("cage")).setExecutor(new CommandCage(this));
        addTabCompletion();
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new SpawnerBreakEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new SpawnerPlaceEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new NoSpawnEggs(), this);
        Bukkit.getPluginManager().registerEvents(new NoAnvilRename(), this);
    }


    public void addTabCompletion() {
       Objects.requireNonNull(this.getCommand("cage")).setTabCompleter(new CageTC());
    }
}
