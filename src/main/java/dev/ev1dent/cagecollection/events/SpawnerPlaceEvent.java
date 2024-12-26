package dev.ev1dent.cagecollection.events;

import dev.ev1dent.cagecollection.CCMain;
import dev.ev1dent.cagecollection.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpawnerPlaceEvent implements Listener {

    private final Utils Utils = new Utils();

    CCMain plugin;
    public SpawnerPlaceEvent(CCMain plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event){
        if(event.isCancelled()) return;
        if (event.getBlock().getType() != Material.SPAWNER) return;

        ItemStack item = event.getItemInHand();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        String mobType = container.get(new NamespacedKey(plugin, "mobType"), PersistentDataType.STRING);

        CreatureSpawner spawner = (CreatureSpawner) event.getBlockPlaced().getState();
        try{
            spawner.setSpawnedType(EntityType.valueOf(mobType));
        } catch (Exception e){
          plugin.getLogger().warning("Unknown Spawner placed. Defaulting to PIG");
            spawner.setSpawnedType(EntityType.PIG);
        }
        spawner.update();
        event.getPlayer().sendMessage(Utils.formatMM(mobType + "Spawner placed."));

    }
}
