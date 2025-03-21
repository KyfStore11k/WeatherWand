package com.kyfstore.weatherWand;

import com.kyfstore.weatherWand.command.Commands;
import com.kyfstore.weatherWand.customlibs.papi.PrimaryWeatherWandPAPIExpansion;
import com.kyfstore.weatherWand.util.config.WeatherWandConfig;
import dev.jorel.commandapi.CommandAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

import com.kyfstore.EZConfigFileAPI.v1.api.ConfigHandler;

public final class WeatherWand extends JavaPlugin {

    public static String weatherwand_identifier = "weatherwand";

    @Override
    public void onLoad() {
        // Save Config
        saveDefaultConfig();

        // Instantiate new Config
        new WeatherWandConfig(this);

        // Load CommandAPI
        CommandAPI.onLoad(Commands.getCommandAPIConfig(this));
        // Register Commands
        Commands.registerCommands(this);
    }

    @Override
    public void onEnable() {
        // Enable InvUI
        InvUI.getInstance().setPlugin(this);

        // Enable CommandAPI
        CommandAPI.onEnable();

        // Enable PlaceholderAPI hooks
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PrimaryWeatherWandPAPIExpansion(this).register();
        }

        ConfigHandler primaryConfig = new ConfigHandler(this, "config.yml");

        primaryConfig.setHeader("DO NOT TOUCH THIS CONFIG FILE UNLESS YOU KNOW EXACTLY WHAT YOU'RE DOING!");
        primaryConfig.saveConfigWithHeaderAndFooter();
    }

    @Override
    public void onDisable() {
        // Disable CommandAPI
        CommandAPI.onDisable();
    }
}
