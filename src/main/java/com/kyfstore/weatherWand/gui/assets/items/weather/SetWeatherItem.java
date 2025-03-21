package com.kyfstore.weatherWand.gui.assets.items.weather;

import com.kyfstore.weatherWand.gui.WeatherWandSetWeatherGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.ControlItem;

public class SetWeatherItem extends ControlItem<PagedGui<?>> {

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        return new ItemBuilder(Material.TRIDENT).setDisplayName("Set Weather");
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        WeatherWandSetWeatherGui gui = new WeatherWandSetWeatherGui(player);
        gui.setup();

        notifyWindows();
    }
}