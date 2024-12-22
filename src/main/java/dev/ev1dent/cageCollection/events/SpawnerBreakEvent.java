package dev.ev1dent.cageCollection.events;


import dev.ev1dent.cageCollection.CCMain;
import dev.ev1dent.cageCollection.Utilities.SpawnerBuilder;
import dev.ev1dent.cageCollection.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SpawnerBreakEvent implements Listener {

    Utils Utils = new Utils();

    CCMain plugin;
    public SpawnerBreakEvent(CCMain plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(event.isCancelled()) return;

        if (block.getType() != Material.SPAWNER) return;
        if (!player.hasPermission("cagecollection.mine")) {
            player.sendMessage(Utils.formatMM("<dark_red> You do not have permission break spawners!"));
            event.setCancelled(true);
            return;
        }
        if(!hasCorrectPickaxe(player)){
            player.sendMessage(Utils.formatMM("<dark_red>You need a better pickaxe to break spawners!"));
            event.setCancelled(true);
            return;
        }
        if(!hasCorrectEnchantments(player)){
            player.sendMessage("<dark_red>You need a Silk Touch pickaxe to collect spawners.");
            event.setCancelled(true);
            return;
        }

        // Get the type of mob in the spawner
        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        String mobType = spawner.getSpawnedType().toString();


        ItemStack spawnerItem = new SpawnerBuilder(plugin)
                .setDisplayName("<green>" + mobType + " Spawner</green>")
                .setMobType(mobType)
                .build();

        block.getWorld().dropItemNaturally(block.getLocation(), spawnerItem);

        event.setDropItems(false);
        event.setExpToDrop(0);
    }

    public boolean hasCorrectPickaxe(Player player){
        ItemStack tool = player.getInventory().getItemInMainHand();

        ArrayList<String> toolType = new ArrayList<>();
        toolType.add("IRON_PICKAXE");
        toolType.add("DIAMOND_PICKAXE");
        toolType.add("NETHERITE_PICKAXE");

        return toolType.contains(tool.getType().toString());
    }

    public boolean hasCorrectEnchantments(Player player){
        ItemStack tool = player.getInventory().getItemInMainHand();
        return tool.containsEnchantment(Enchantment.SILK_TOUCH);
    }
}