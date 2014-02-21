package com.codermason.gatewayeco.cmds;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.cmds.extra.EcoCommand;
import com.codermason.gatewayeco.perms.Permissions;
import com.codermason.gatewayeco.utils.Messenger;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * created by codermason on 2/20/14
 */
public class PayCommand extends EcoCommand {

    public PayCommand(GatewayEco plugin) {
        super(plugin);
    }

    public void execute(CommandSender sender, Command command, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("pay")) return;

        if(!Permissions.COMMANDS_PAY.validate(sender)) {
            Messenger.noPermissions(sender);
            return;
        }else if(!(sender instanceof Player)) {
            Messenger.noConsole();
            Messenger.sendMessage(Bukkit.getConsoleSender(), "If you want to give a player money, use /money give <player> <amount>");
            return;
        }

        if(args.length == 2) {
            String player = args[0], rawAmount = args[1];
            if(NumberUtils.isNumber(rawAmount)) {
                int amount = Integer.parseInt(rawAmount);
                if(super.getPlugin().getBank().getAccount(sender.getName()).hasAtLeast(amount)) {
                    if(super.getPlugin().getBank().getAccount(player).canReceivePay()) {
                        super.getPlugin().getBank().getAccount(sender.getName()).payAccount(super.getPlugin().getBank().getAccount(player), Integer.parseInt(rawAmount));
                        Messenger.sendMessage(sender, "Payed "+player+" $"+rawAmount+"!");
                        if(Bukkit.getPlayer(player) != null) Messenger.sendMessage(Bukkit.getPlayer(player), "Received $"+rawAmount+" from "+sender.getName()+"!");
                    }else{
                        Messenger.sendMessage(sender, player+" cannot receive payments!");
                    }
                }else{
                    Messenger.sendMessage(sender, "You do not have enough money!");
                }
            }else{
                Messenger.sendMessage(sender, "Integer expected; string received");
            }
        }else{
            Messenger.usage(sender, Permissions.COMMANDS_PAY.getUsage());
        }
    }

}