package dev.ev1dent.cageCollection.Utilities;

import dev.ev1dent.cageCollection.CCMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class SpawnerBuilder {

    private CCMain CCInstance(){
        return CCMain.getPlugin(CCMain.class);
    }

    Utils Utils = new Utils();

    private String displayName;
    private String mobType;


    public SpawnerBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public SpawnerBuilder setMobType(String mobType) {
        this.mobType = mobType;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.SPAWNER);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.displayName(Utils.formatMM(displayName));
            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(new NamespacedKey(CCInstance(), "mob-type"), PersistentDataType.STRING, mobType);



            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }
}
