package com.codermason.gatewayeco.cmds.moneycmds;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.cmds.extra.EcoCommand;
import com.codermason.gatewayeco.perms.Permissions;
import com.codermason.gatewayeco.utils.Messenger;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/20/14
 */
public class SetCommand extends EcoCommand {

    public SetCommand(GatewayEco plugin) {
        super(plugin);
    }

    public void execute(CommandSender sender, Command command, String[] args) {
        if(!args[0].equalsIgnoreCase("set")) return;

        if(!Permissions.COMMANDS_MONEY_SET.validate(sender)) {
            Messenger.noPermissions(sender);
            return;
        }

        if(args.length == 3) {
            String player = args[1], amount = args[2];
            if(NumberUtils.isNumber(amount)) {
                super.getPlugin().getBank().getAccount(player).setBalance(Integer.parseInt(amount));
                Messenger.sendMessage(sender, "Set "+player+"'s balance to $"+amount+"!");
            }else{
                Messenger.sendMessage(sender, "Integer expected; string received");
            }
        }else{
            Messenger.usage(sender, Permissions.COMMANDS_MONEY_SET.getUsage());
        }
    }

}