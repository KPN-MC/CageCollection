package dev.ev1dent.cagecollection.utilities;

import dev.ev1dent.cagecollection.CCMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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

    private String displayName;
    private String mobType;
//    private String loreLine;


    public SpawnerBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public SpawnerBuilder setMobType(String mobType) {
        this.mobType = mobType;
        return this;
    }

//    public SpawnerBuilder setLore(String line) {
//        this.loreLine = line;
//        return this;
//    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            // Itemname
            meta.displayName(Utils.formatMM(displayName));

//            // Lore
//            List<Component> lore = new ArrayList<>();
//            lore.add(Utils.formatMM(loreLine));
//            meta.lore(lore);

            // PDC for mob type
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(mobTypeKey, PersistentDataType.STRING, mobType);

            //process the meta on item
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }
}
