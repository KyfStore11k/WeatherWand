package com.kyfstore.weatherWand.gui.assets.items;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.impl.CommandItem;

public class CustomCommandItem extends CommandItem {
    private final String command;

    public CustomCommandItem(@NotNull ItemProvider itemProvider, @NotNull String command) {
        super(itemProvider, command);
        this.command = command;
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        player.chat(command);
    }
}
