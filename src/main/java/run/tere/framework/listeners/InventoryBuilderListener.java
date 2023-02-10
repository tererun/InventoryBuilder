package run.tere.framework.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import run.tere.framework.InventoryBuilder;
import run.tere.framework.InventoryBuilderProvider;
import run.tere.framework.models.ClickInventoryItem;
import run.tere.framework.models.InventoryItem;

public class InventoryBuilderListener implements Listener {

    private InventoryBuilderProvider inventoryBuilderProvider;

    public void setInventoryBuilderProvider(InventoryBuilderProvider inventoryBuilderProvider) {
        this.inventoryBuilderProvider = inventoryBuilderProvider;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null) return;

        for (InventoryBuilder inventoryBuilder : inventoryBuilderProvider.getRegisteredInventoryBuilders()) {
            if (inventoryBuilder.getCreatedInventory().equals(e.getClickedInventory())) {
                for (InventoryItem inventoryItem : inventoryBuilder.getKeyItemMap().values()) {
                    if (inventoryItem.getItemStack().equals(clickedItem)) {
                        if (inventoryItem.isCancelled()) e.setCancelled(true);
                        if (inventoryItem instanceof ClickInventoryItem clickInventoryItem) {
                            clickInventoryItem.getClickAction().click(e);
                        }
                    }
                }
            }
        }
    }

}
