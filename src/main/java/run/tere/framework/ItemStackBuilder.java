package run.tere.framework;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackBuilder {

    private Material material;
    private int amount;
    private String displayName;
    private List<String> lore;

    public ItemStackBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.displayName = null;
        this.lore = null;
    }

    public ItemStackBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemStackBuilder setLore(String...lore) {
        this.lore = new ArrayList<>(Arrays.asList(lore));
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (displayName != null) itemMeta.setDisplayName(displayName);
        if (lore != null) itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
