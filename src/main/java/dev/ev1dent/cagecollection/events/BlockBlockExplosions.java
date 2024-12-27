package dev.ev1dent.cagecollection.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class BlockBlockExplosions  implements Listener {

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent event) {
        event.blockList().forEach(block -> {
            if (block.getType() == Material.SPAWNER){
                event.blockList().remove(block);
            }

        });
    }
}
