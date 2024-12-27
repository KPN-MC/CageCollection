package dev.ev1dent.cagecollection.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockEntityExplosions implements Listener {

    @EventHandler
    public void onEntityExplosion(EntityExplodeEvent event) {
        event.blockList().forEach(block -> {
            if (block.getType() == Material.SPAWNER){
                event.blockList().remove(block);
            }

        });
    }
}
