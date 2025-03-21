package com.kyfstore.weatherWand.gui.assets.items.time;

import com.kyfstore.weatherWand.gui.WeatherWandSetTimeGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class SetTimeItem extends AbstractItem {
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.CLOCK).setDisplayName("Set Time");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent inventoryClickEvent) {
        WeatherWandSetTimeGui gui = new WeatherWandSetTimeGui(player);
        gui.setup();

        notifyWindows();
    }
}
