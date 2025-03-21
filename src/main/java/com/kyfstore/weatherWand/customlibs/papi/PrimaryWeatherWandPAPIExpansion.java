package com.kyfstore.weatherWand.customlibs.papi;

import com.kyfstore.weatherWand.WeatherWand;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class PrimaryWeatherWandPAPIExpansion extends PlaceholderExpansion {

    private final WeatherWand plugin;
    private final World world;

    public PrimaryWeatherWandPAPIExpansion(WeatherWand plugin) {
        this.plugin = plugin;
        this.world = Bukkit.getWorld("world");
    }

    @Override
    @NotNull
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors()); //
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return WeatherWand.weatherwand_identifier;
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("current-weather")) {
            return plugin.getConfig().getString("placeholders.current-weather", getWeatherType()); //
        }

        if (params.equalsIgnoreCase("current-time")) {
            return plugin.getConfig().getString("placeholders.current-time", getMineraftWorldTime("default"));
        }

        if (params.equalsIgnoreCase("current-time-fancy")) {
            return plugin.getConfig().getString("placeholders.current-time-fancy", getMineraftWorldTime("fancy"));
        }

        return null;
    }

    private String getMineraftWorldTime(String type) {
        if (world != null) {
            long time = world.getTime();
            if (type.equalsIgnoreCase("default")) {
                return String.valueOf(time);
            } else if (type.equalsIgnoreCase("fancy")) {
                if (time < 6000) {
                    return "morning";
                } else if (time < 12000) {
                    return "noon";
                } else if (time < 18000) {
                    return "evening";
                } else {
                    return "night";
                }
            } else return null;
        } else return null;
    }

    private String getWeatherType() {
        if (world != null) {
            if (world.hasStorm()) {
                if (world.isThundering()) {
                    return "thunderstorm";
                } else {
                    return "rain";
                }
            } else {
                return "clear";
            }
        } else return null;
    }
}