package dev.ev1dent.cagecollection.commands;

import dev.ev1dent.cagecollection.CCMain;
import dev.ev1dent.cagecollection.utilities.SpawnerBuilder;
import dev.ev1dent.cagecollection.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandCage implements CommandExecutor {

    Utils Utils = new Utils();
    CCMain plugin;
    public CommandCage(CCMain plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(args.length < 3) {
            return false;
        }

        String action = args[0].toLowerCase();
        Player player = Bukkit.getPlayer(args[1]);
        if(player == null) {
            sender.sendMessage(Utils.formatMM("<red>Player not found."));
            return true;
        }

        switch (action){
            case "give" -> {
                String mobType = args[2];
                ItemStack spawnerItem = new SpawnerBuilder(plugin)
                        .setDisplayName("<green>" + mobType + " Spawner</green>")
                        .setMobType(mobType)
                        .build();

                HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(spawnerItem);
                if (!hashMap.isEmpty()) {
                    player.getWorld().dropItem(player.getLocation(), spawnerItem);
                }

            }
            case "set" ->{
                // set spawner in hand
                if(!(sender instanceof Player p)) {
                    sender.sendMessage(Utils.formatMM("<red>You must be a player to use this command!"));
                    return false;
                }

                ItemStack item = p.getInventory().getItemInMainHand();
                if(item == null || item.getType() == Material.AIR) {
                    sender.sendMessage(Utils.formatMM("<red>You are not holding any item!"));
                    return false;
                }

                String mobType = args[2];
                ItemStack newItem = new SpawnerBuilder(plugin)
                        .setDisplayName("<green>" + mobType + " Spawner</green>")
                        .setMobType(mobType)
                        .build();
                HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(newItem);
                if (!hashMap.isEmpty()) {
                    player.getWorld().dropItem(player.getLocation(), newItem);
                }

            }
        }

        return false;
    }
}
