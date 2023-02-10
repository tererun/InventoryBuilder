package run.tere.framework.models;

import org.bukkit.inventory.ItemStack;

public class ClickInventoryItem extends InventoryItem {

    private ClickAction clickAction;

    public ClickInventoryItem(ItemStack itemStack, ClickAction clickAction) {
        super(itemStack);
        this.clickAction = clickAction;
    }

    public ClickInventoryItem(ItemStack itemStack, ClickAction clickAction, boolean cancelled) {
        super(itemStack, cancelled);
        this.clickAction = clickAction;
    }

    public ClickAction getClickAction() {
        return clickAction;
    }

}
