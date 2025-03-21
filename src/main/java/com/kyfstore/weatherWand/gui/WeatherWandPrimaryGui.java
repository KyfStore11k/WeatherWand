package com.kyfstore.weatherWand.gui;

import com.kyfstore.weatherWand.gui.assets.items.time.SetTimeItem;
import com.kyfstore.weatherWand.gui.assets.items.weather.SetWeatherItem;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class WeatherWandPrimaryGui {
    private static Player player;

    public WeatherWandPrimaryGui(CommandSender commandSender) {
        this.player = (Player) commandSender;
    }

    public void setup() {
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));

        Gui gui = PagedGui.items()
                .setStructure(
                        "# # # # # # # # #",
                        "# w t x x x x x #",
                        "# x x x x x x x #",
                        "# # # # # # # # #"
                )
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', border)
                .addIngredient('w', new SetWeatherItem())
                .addIngredient('t', new SetTimeItem())
                .build();

        Window window = Window.single()
                .setViewer(this.player)
                .setTitle("WeatherWand")
                .setGui(gui)
                .build();

        window.open();
    }

}
