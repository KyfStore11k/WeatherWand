package com.kyfstore.weatherWand.command;

import com.kyfstore.weatherWand.gui.WeatherWandPrimaryGui;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class Commands {

    private static FileConfiguration globalPluginConfig;

    public static void registerCommands(JavaPlugin plugin) {
        globalPluginConfig = plugin.getConfig();

        new CommandAPICommand("weatherwand")
                .withPermission("weatherwand")
                .withAliases("ww", "weatherw", "wwand")
                .executes((sender, args) -> {
                    sender.sendMessage(mmdText("<white><bold>========================================"));
                    sender.sendMessage(mmdText("<white><bold><italic>             Help Menu             "));
                    sender.sendMessage(mmdText("<white><bold>========================================"));
                    sender.sendMessage(mmdText("<reset> "));
                    sender.sendMessage(mmdText("<green><bold>/weatherwand (Aliases: /ww, /weatherw, /wwand): <#78ff9c>Shows this help screen!"));
                    sender.sendMessage(mmdText("<green><bold>/weatherwand help <command> (Aliases: /weatherwand h): <#78ff9c>Prints out how the requested command works in more detail!"));
                    sender.sendMessage(mmdText("<green><bold>/weatherwand openGui (Aliases: /weatherwand opengui, /weatherwand og): <#78ff9c>Open's the WeatherWand GUI!"));
                    sender.sendMessage(mmdText("<reset> "));
                    sender.sendMessage(mmdText("<white><bold>========================================"));
                    sender.sendMessage(mmdText("<reset> "));
                })
                .withSubcommand(
                        new CommandAPICommand("openGui")
                                .withPermission("weatherwand.canOpenGui")
                                .withAliases("opengui", "og")
                                .executes((sender, args) -> {
                                    if (!(sender instanceof Player player)) {
                                        sender.sendMessage("Cannot open WeatherWand GUI on the console!");
                                        return;
                                    }
                                    WeatherWandPrimaryGui gui = new WeatherWandPrimaryGui(player);
                                    gui.setup();
                                }
                        )
                )
                .withSubcommand(
                        new CommandAPICommand("help")
                                .withPermission("weatherwand.help")
                                .withAliases("h")
                                .withArguments(new StringArgument("command"))
                                .executes((sender, args) -> {
                                    String helpCommand = args.get("command").toString();

                                    if (globalPluginConfig.contains(String.format("settings.commands.%s", helpCommand))) {
                                        sendHelpMessage(sender, helpCommand);
                                        return;
                                    }

                                    String resolvedCommand = null;
                                    for (String command : globalPluginConfig.getConfigurationSection("settings.commands").getKeys(false)) {
                                        List<String> aliases = globalPluginConfig.getStringList(String.format("settings.commands.%s.aliases", command));
                                        if (aliases.contains(helpCommand)) {
                                            resolvedCommand = command;
                                            break;
                                        }
                                    }

                                    if (resolvedCommand != null) {
                                        sendHelpMessage(sender, resolvedCommand);
                                    } else {
                                        sender.sendMessage(mmdText(String.format("<red>Command: %s; does not exist!", helpCommand)));
                                    }
                                })
                )
                .register();
    }

    public static CommandAPIBukkitConfig getCommandAPIConfig(JavaPlugin plugin) {
        CommandAPIBukkitConfig commandAPIConfig = new CommandAPIBukkitConfig(plugin);

        commandAPIConfig.silentLogs(!plugin.getConfig().getBoolean("settings.general.verbose"));
        commandAPIConfig.verboseOutput(plugin.getConfig().getBoolean("settings.general.verbose"));
        commandAPIConfig.useLatestNMSVersion(false);
        commandAPIConfig.beLenientForMinorVersions(true);
        commandAPIConfig.missingExecutorImplementationMessage("No executor Impl? Weird? (Create a github issue)");
        commandAPIConfig.shouldHookPaperReload(true);
        commandAPIConfig.skipReloadDatapacks(false);
        commandAPIConfig.usePluginNamespace();

        return commandAPIConfig;
    }

    private static Component mmdText(String input) {
        return miniMessage().deserialize(input);
    }

    private static void sendHelpMessage(CommandSender sender, String command) {
        sender.sendMessage(mmdText("<white><bold>========================================"));
        sender.sendMessage(mmdText(String.format(
                "<green><bold>Command Name: %s; Description: %s",
                command,
                globalPluginConfig.get(String.format("settings.commands.%s.description", command))
        )));
        sender.sendMessage(mmdText("<white><bold>========================================"));
        sender.sendMessage(mmdText("<reset> "));
    }
}
