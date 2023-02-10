package run.tere.framework.models;

import org.bukkit.inventory.ItemStack;

public class InventoryItem {

    private ItemStack itemStack;
    private boolean cancelled;

    public InventoryItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.cancelled = false;
    }

    public InventoryItem(ItemStack itemStack, boolean cancelled) {
        this.itemStack = itemStack;
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
