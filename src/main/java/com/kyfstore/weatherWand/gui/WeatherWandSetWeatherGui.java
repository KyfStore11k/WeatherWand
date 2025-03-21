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

public class WeatherWandSetWeatherGui {
    private static Player player;

    public WeatherWandSetWeatherGui(CommandSender commandSender) {
        this.player = (Player) commandSender;
    }

    public void setup() {
        Item border = new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE));

        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# c r t x x x x #",
                        "# x x x x x x b #",
                        "# # # # # # # # #"
                )
                .addIngredient('#', border)
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('c', new CustomCommandItem(new ItemBuilder(Material.YELLOW_WOOL).setDisplayName("Set Clear Weather"), "/weather clear"))
                .addIngredient('r', new CustomCommandItem(new ItemBuilder(Material.BLUE_WOOL).setDisplayName("Set Rainy Weather"), "/weather rain"))
                .addIngredient('t', new CustomCommandItem(new ItemBuilder(Material.GRAY_WOOL).setDisplayName("Set Stormy Weather"), "/weather thunder"))
                .addIngredient('b', new CustomCommandItem(new ItemBuilder(Material.BARRIER).setDisplayName("Go Back!"), "/weatherwand openGui"))
                .build();

        Window window = Window.single()
                .setViewer(this.player)
                .setTitle("[WeatherWand] Set Weather")
                .setGui(gui)
                .build();

        window.open();
    }
}
