package com.kyfstore.weatherWand.util.config;

import com.kyfstore.weatherWand.WeatherWand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class WeatherWandConfig {
    private static WeatherWandConfig WeatherWandConfig;
    private final WeatherWand globalPlugin;
    private FileConfiguration globalConfig;

    public WeatherWandConfig(WeatherWand globalPlugin) {
        WeatherWandConfig = this;
        this.globalPlugin = globalPlugin;
        this.globalConfig = globalPlugin.getConfig();
    }

    public static WeatherWandConfig getInstance() {
        return WeatherWandConfig;
    }

    public void reloadConfig() {
        globalPlugin.saveDefaultConfig();
        globalPlugin.reloadConfig();
        this.globalConfig = globalPlugin.getConfig();
    }

    public void setValue(String valuePath, Object value) {
        if (globalConfig.contains(valuePath)) {
            if (value instanceof String && ("true".equalsIgnoreCase((String) value) || "false".equalsIgnoreCase((String) value))) {
                globalConfig.set(valuePath, Boolean.parseBoolean((String) value));
            } else {
                globalConfig.set(valuePath, value);
            }
            globalPlugin.saveConfig();
        }
    }

    public List<Object> showValues() {
        return traverseConfig(globalConfig);
    }

    private List<Object> traverseConfig(ConfigurationSection section) {
        List<Object> list = new ArrayList<>();
        list.addAll(section.getKeys(true));
        return list;
    }
}
