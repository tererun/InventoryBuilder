# InventoryBuilder

Spigotでインベントリを簡単に作れるようにするビルダーです。

## Features

- [x] Build pattern
- [x] Method Chaining
- [x] ItemStack builder
- [x] Easy to use

## How to use

### Install

[![](https://jitpack.io/v/tererun/InventoryBuilder.svg)](https://jitpack.io/#tererun/InventoryBuilder)

**Maven**
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.tererun</groupId>
        <artifactId>InventoryBuilder</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```

**Gradle**
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.tererun:InventoryBuilder:Tag'
}
```

### Register InventoryBuilder

```java
public class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        Plugin plugin = this;
        
        InventoryBuilderProvider.register(plugin);
    }

}
```

### Create new inventory

```java
Plugin plugin = ...;

Inventory inventory = new InventoryBuilder("Inventory Title", 27)
    .addRow(new ItemRow("AAAAAAAAA"))
        .addRow(new ItemRow("AAASADAAA"))
        .addRow(new ItemRow("AAAAAAAAA"))
        .setItem('A', new InventoryItem(new ItemStack(Material.AIR)))
        .setItem('S', new ClickInventoryItem(new ItemStackBuilder(Material.STONE, 1)
                .setDisplayName("§aNormal Stone")
                .setLore("§oYou can get this stone")
                .build(),
                event -> {
                    event.getWhoClicked().sendMessage("This is stone!");
                }, false))
        .setItem('D', new ClickInventoryItem(new ItemStackBuilder(Material.DIAMOND, 1)
                .setDisplayName("§c§lFake Diamond")
                .setLore("§oDon't touch this")
                .build(),
                event -> {
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        event.getWhoClicked().setHealth(0);
                    });
                }, true))
        .build(plugin);
```