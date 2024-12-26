package dev.ev1dent.cagecollection.events;

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class NoSpawnEggs implements Listener {

    @EventHandler
    public void onEggUsage(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getClickedBlock() == null || !event.getClickedBlock().getType().equals(Material.SPAWNER)) return;

        if (isHoldingSpawnEgg(player) && !isAdmin(player)) {
            event.setCancelled(true);
        }
    }

    private boolean isAdmin(@NotNull Player player) {
        return player.isSneaking() &&
                player.hasPermission("cagecollection.break.override") &&
                player.getGameMode() == GameMode.CREATIVE;
    }

    private boolean isHoldingSpawnEgg(@NotNull Player player) {
        return MaterialTags.SPAWN_EGGS.isTagged(player.getInventory().getItemInMainHand()) ||
                MaterialTags.SPAWN_EGGS.isTagged(player.getInventory().getItemInOffHand());
    }
}