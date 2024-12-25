package dev.ev1dent.cagecollection.events;

import com.destroystokyo.paper.MaterialTags;
import dev.ev1dent.cagecollection.CCMain;
import dev.ev1dent.cagecollection.utilities.SpawnerBuilder;
import dev.ev1dent.cagecollection.utilities.Utils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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
        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        if (!player.hasPermission("cagecollection.break")) {
            player.sendMessage(Utils.formatMM("<dark_red> You do not have permission break spawners!"));
            event.setCancelled(true);
            return;
        }

        if(isAdmin(player)){
            Location loc = block.getLocation();
            String coordinates  = loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
            plugin.getLogger().warning("Admin: " + player.getName() + " Broke a " + spawner.getSpawnedType() + " spawner at " + coordinates);
            return;
        }

        if(!hasAPickaxe(player)) {
            player.sendMessage(Utils.formatMM("<dark_red>You need a pickaxe to break spawners!"));
            event.setCancelled(true);
            return;
        }

        if(!hasCorrectPickaxe(player)){
            player.sendMessage(Utils.formatMM("<dark_red>You need a better pickaxe to break spawners!"));
            event.setCancelled(true);
            return;
        }
        if(!hasCorrectEnchantments(player)){
            player.sendMessage(Utils.formatMM("<dark_red>You need a Silk Touch pickaxe to collect spawners."));
            event.setCancelled(true);
            return;
        }

        if(spawner.getSpawnedType() == null) return;
        String mobType = spawner.getSpawnedType().toString();

        ItemStack spawnerItem = new SpawnerBuilder(plugin)
                .setMobType(mobType)
                .setSpawnerQuantity(1)
                .build();

        block.getWorld().dropItemNaturally(block.getLocation(), spawnerItem);

        event.setDropItems(false);
        event.setExpToDrop(0);
    }

    private boolean hasAPickaxe(Player player){
        ItemStack item = player.getInventory().getItemInMainHand();
        return MaterialTags.PICKAXES.isTagged(item.getType());
    }

    private boolean hasCorrectPickaxe(Player player){
        ItemStack tool = player.getInventory().getItemInMainHand();

        ArrayList<String> toolType = new ArrayList<>();
        toolType.add("IRON_PICKAXE");
        toolType.add("DIAMOND_PICKAXE");
        toolType.add("NETHERITE_PICKAXE");

        return toolType.contains(tool.getType().toString());
    }

    private boolean hasCorrectEnchantments(Player player){
        ItemStack tool = player.getInventory().getItemInMainHand();
        return tool.containsEnchantment(Enchantment.SILK_TOUCH);
    }

    private boolean isAdmin(Player player){
        return player.isSneaking()
                && player.hasPermission("cagecollection.break.override")
                && player.getGameMode() == GameMode.CREATIVE;
    }
}