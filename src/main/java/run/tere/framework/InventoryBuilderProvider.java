package run.tere.framework;

import org.bukkit.plugin.Plugin;
import run.tere.framework.listeners.InventoryBuilderListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryBuilderProvider {

    private static HashMap<Plugin, InventoryBuilderProvider> inventoryBuilderProviderMap;
    private Plugin plugin;
    private InventoryBuilderListener inventoryBuilderListener;
    private List<InventoryBuilder> registeredInventoryBuilders;

    public InventoryBuilderProvider(Plugin plugin, InventoryBuilderListener inventoryBuilderListener) {
        this.plugin = plugin;
        this.inventoryBuilderListener = inventoryBuilderListener;
        this.registeredInventoryBuilders = new ArrayList<>();
    }

    public static HashMap<Plugin, InventoryBuilderProvider> getInventoryBuilderProviderMap() {
        return inventoryBuilderProviderMap;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public InventoryBuilderListener getInventoryBuilderListener() {
        return inventoryBuilderListener;
    }

    public List<InventoryBuilder> getRegisteredInventoryBuilders() {
        return registeredInventoryBuilders;
    }

    public static void register(Plugin plugin) {
        InventoryBuilderListener inventoryBuilderListener = new InventoryBuilderListener();
        InventoryBuilderProvider inventoryBuilderProvider = new InventoryBuilderProvider(plugin, inventoryBuilderListener);
        inventoryBuilderListener.setInventoryBuilderProvider(inventoryBuilderProvider);

        inventoryBuilderProviderMap.put(plugin, inventoryBuilderProvider);
        plugin.getServer().getPluginManager().registerEvents(inventoryBuilderListener, plugin);
    }

}
