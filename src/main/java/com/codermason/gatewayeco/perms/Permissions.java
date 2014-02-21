package com.codermason.gatewayeco.perms;

import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/17/14
 */
public enum Permissions {

    COMMANDS_MONEY_GIVE("money give <player> <amount>", "give a player funds"),
    COMMANDS_MONEY_TAKE("money take <player> <amount>", "take a player's funds"),
    COMMANDS_MONEY_SET("money set <player> <amount>", "set a player's funds"),
    COMMANDS_MONEY_TOP("money top <page>", "view the richest players"),

    COMMANDS_TOGGLEPAY("togglepay", "toggle your received payments"),
    COMMANDS_BALANCE("balance", "check your balance"),
    COMMANDS_PAY("pay <player> <amount>", "pay a player funds");

    private String usage, description;

    Permissions(String usage, String description) {
        this.usage = usage;
        this.description = description;
    }

    public String getUsage() {return this.usage;}

    public String getDescription() {return this.description;}

    public boolean validate(CommandSender sender) {
        return sender.hasPermission(toString()) || sender.hasPermission("gatewayeco.*");
    }

    public String toString() {
        return "gatewayeco."+this.name().toLowerCase().replace("_", ".");
    }


}
