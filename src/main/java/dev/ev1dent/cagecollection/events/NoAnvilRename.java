package dev.ev1dent.cagecollection.events;

import dev.ev1dent.cagecollection.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class NoAnvilRename implements Listener {

    private final Utils Utils = new Utils();
    @EventHandler
    public void onRename(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (event.getInventory().getType() != InventoryType.ANVIL) return;
        if (event.getCurrentItem().getType() != Material.SPAWNER) return;

        Player player = (Player) event.getWhoClicked();
        player.sendMessage(Utils.errorMM("You cannot rename this item in an anvil!"));
        event.setCancelled(true);
    }
}