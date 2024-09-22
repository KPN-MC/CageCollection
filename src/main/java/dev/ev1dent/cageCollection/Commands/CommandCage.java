package dev.ev1dent.cageCollection.Commands;

import dev.ev1dent.cageCollection.Utilities.SpawnerBuilder;
import dev.ev1dent.cageCollection.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandCage implements CommandExecutor {

    Utils Utils = new Utils();

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
                String type = args[2];
                ItemStack spawner = new SpawnerBuilder()
                        .setMobType(type)
                        .setDisplayName()
                        .build();

            }
            case "set" ->{
                // set spawner in hand
                if(!(sender instanceof Player player)) {
                    return false;
                }
            }
        }

        return false;
    }
}
