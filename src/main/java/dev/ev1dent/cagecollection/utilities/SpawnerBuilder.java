package dev.ev1dent.cagecollection.utilities;

import dev.ev1dent.cagecollection.CCMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class SpawnerBuilder {
    Utils Utils = new Utils();

    private final NamespacedKey mobTypeKey;

    public SpawnerBuilder(CCMain plugin) {
        this.mobTypeKey = new NamespacedKey(plugin, "mobType");
    }

    private String mobType;
    private int spawnerQuantity;


    public SpawnerBuilder setMobType(String mobType) {
        this.mobType = mobType;
        return this;
    }

    public SpawnerBuilder setSpawnerQuantity(int spawnerQuantity) {
        this.spawnerQuantity = spawnerQuantity;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            // Itemname
            meta.displayName(Utils.formatMM("<green>"  + processSpawnerName(mobType) + "</green>"));

            // PDC for mob type
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(mobTypeKey, PersistentDataType.STRING, mobType);

            meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

            //process the meta on item
            itemStack.setItemMeta(meta);

            try {
                itemStack.setAmount(spawnerQuantity);
            } catch (Exception e) {
                itemStack.setAmount(1);
            }

        }
        return itemStack;
    }

    private String processSpawnerName(String mobType){
        String[] words = mobType.split("_");
        StringBuilder formattedString = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return formattedString.toString().trim() + " Spawner";
    }
}
