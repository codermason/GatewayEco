package com.codermason.gatewayeco.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/20/14
 */
public class Messenger {

    private static String PREFIX = ChatColor.GOLD+"["+ChatColor.GRAY+"GatewayEco"+ChatColor.GOLD+"]"+ChatColor.RESET+" ";

    public static void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(PREFIX+msg);
    }

    public static void noConsole() {
        sendMessage(Bukkit.getConsoleSender(), "Only players can execute this command!");
    }

    public static void noPermissions(CommandSender sender) {
        sendMessage(sender, "You do not have permission!");
    }

    public static void usage(CommandSender sender, String usage) {
        sendMessage(sender, "Usage: /"+usage);
    }

}
