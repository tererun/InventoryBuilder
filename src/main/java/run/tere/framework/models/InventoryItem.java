package run.tere.framework.models;

import org.bukkit.inventory.ItemStack;

public class InventoryItem {

    private ItemStack itemStack;

    public InventoryItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
