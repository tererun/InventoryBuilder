package run.tere.framework;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import run.tere.framework.models.InventoryItem;
import run.tere.framework.models.ItemRow;

import java.util.*;

public class InventoryBuilder {

    private UUID uuid;
    private Inventory createdInventory;
    private String title;
    private int size;
    private List<ItemRow> rows;
    private Map<Character, InventoryItem> keyItemMap;

    public InventoryBuilder(String title, int size) {
        if (size % 9 != 0) {
            throw new IllegalArgumentException("Size must be a multiple of 9");
        }
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.size = size;
        this.rows = new ArrayList<>();
        this.keyItemMap = new HashMap<>();
    }

    public InventoryBuilder addRow(ItemRow itemRow) {
        rows.add(itemRow);
        return this;
    }

    public InventoryBuilder setItem(char key, InventoryItem inventoryItem) {
        keyItemMap.put(key, inventoryItem);
        return this;
    }

    private Inventory toInventory() {
        Inventory inventory = Bukkit.createInventory(null, size, title);
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            ItemRow itemRow = rows.get(rowIndex);
            String[] split = itemRow.getPattern().split("");
            for (int splitIndex = 0, splitLength = split.length; splitIndex < splitLength; splitIndex++) {
                String key = split[splitIndex];
                InventoryItem inventoryItem = keyItemMap.get(key.charAt(0));
                if (inventoryItem == null) throw new NullPointerException("Pattern key is not set");
                inventory.setItem(rowIndex + splitIndex, inventoryItem.getItemStack());
            }
        }
        return inventory;
    }

    public Map<Character, InventoryItem> getKeyItemMap() {
        return keyItemMap;
    }

    public Inventory getCreatedInventory() {
        return createdInventory;
    }

    public Inventory build(Plugin plugin) {
        if (rows.size() != size / 9) {
            throw new IllegalArgumentException("Rows is not added enough");
        }
        InventoryBuilderProvider inventoryBuilderProvider = InventoryBuilderProvider.getInventoryBuilderProviderMap().get(plugin);
        if (inventoryBuilderProvider == null) {
            throw new NullPointerException("Plugin is not registered");
        }
        Inventory inventory = toInventory();
        this.createdInventory = inventory;
        inventoryBuilderProvider.getRegisteredInventoryBuilders().add(this);
        return inventory;
    }

}
