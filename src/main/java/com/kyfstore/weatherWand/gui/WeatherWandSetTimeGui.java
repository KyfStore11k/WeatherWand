package com.kyfstore.weatherWand.gui;

import com.kyfstore.weatherWand.gui.assets.items.CustomCommandItem;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class WeatherWandSetTimeGui {
    private static Player player;

    public WeatherWandSetTimeGui(CommandSender commandSender) {
        this.player = (Player) commandSender;
    }

    public void setup() {
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));

        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# d n i m x x x #",
                        "# x x x x x x b #",
                        "# # # # # # # # #"
                )
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', border)
                .addIngredient('d', new CustomCommandItem(new ItemBuilder(Material.WHITE_WOOL).setDisplayName("Set Day Time"), "/time set day"))
                .addIngredient('n', new CustomCommandItem(new ItemBuilder(Material.YELLOW_WOOL).setDisplayName("Set Noon Time"), "/time set noon"))
                .addIngredient('i', new CustomCommandItem(new ItemBuilder(Material.GRAY_WOOL).setDisplayName("Set Night Time"), "/time set night"))
                .addIngredient('m', new CustomCommandItem(new ItemBuilder(Material.BLACK_WOOL).setDisplayName("Set MidNight Time"), "/time set midnight"))
                .addIngredient('b', new CustomCommandItem(new ItemBuilder(Material.BARRIER).setDisplayName("Go Back!"), "/weatherwand openGui"))
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("[WeatherWand] Set Time")
                .setGui(gui)
                .build();

        window.open();
    }
}
