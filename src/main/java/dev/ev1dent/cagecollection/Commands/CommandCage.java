package dev.ev1dent.cagecollection.Commands;

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
            sender.sendMessage(Utils.errorMM("Player not found."));
            return true;
        }

        String mobType = args[2];
        switch (action){
            case "give" -> {

                if(!Utils.getEntityNames(Utils.getLivingEntities()).contains(mobType)){
                    sender.sendMessage(Utils.errorMM("That is not a valid Entity."));
                    return true;
                }

                int quantity;
                try{
                    quantity = Integer.parseInt(args[3]);
                } catch (Exception e){
                    quantity = 1;
                }

                ItemStack spawnerItem = new SpawnerBuilder(plugin)
                        .setMobType(mobType)
                        .setSpawnerQuantity(quantity)
                        .build();

                HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(spawnerItem);

                if (!hashMap.isEmpty()) {
                    player.getWorld().dropItem(player.getLocation(), spawnerItem);
                }
                sender.sendMessage(Utils.formatMM(String.format("Gave %s %s %s Spawner(s)", player.getName(), quantity, mobType)));
            }

            case "set" ->{
                if(!(sender instanceof Player p)) {
                    sender.sendMessage(Utils.errorMM("You must be a player to use this command!"));
                    return true;
                }

                ItemStack item = p.getInventory().getItemInMainHand();

                if(item.getType() == Material.AIR) {
                    sender.sendMessage(Utils.errorMM("You are not holding any item!"));
                    return true;
                }

                if(item.getType() != Material.SPAWNER){
                    sender.sendMessage(Utils.errorMM("You are not holding a spawner!"));
                    return true;
                }

                if(!Utils.getEntityNames(Utils.getLivingEntities()).contains(mobType)){
                    sender.sendMessage(Utils.errorMM("That is not a valid Entity."));
                    return true;
                }

                ItemStack newItem = new SpawnerBuilder(plugin)
                        .setMobType(mobType)
                        .setSpawnerQuantity(item.getAmount())
                        .build();

                player.getInventory().setItemInMainHand(newItem);
                sender.sendMessage(Utils.formatMM("Set spawner to " + mobType));

            }

            default -> sender.sendMessage(Utils.errorMM("Invalid action!"));

        }
        return true;


    }
}
