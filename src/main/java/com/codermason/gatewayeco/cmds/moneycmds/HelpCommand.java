package com.codermason.gatewayeco.cmds.moneycmds;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.cmds.extra.EcoCommand;
import com.codermason.gatewayeco.perms.Permissions;
import com.codermason.gatewayeco.utils.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/20/14
 */
public class HelpCommand extends EcoCommand {

    public HelpCommand(GatewayEco plugin) {
        super(plugin);
    }

    public void execute(CommandSender sender, Command command, String[] args) {
        if(!args[0].equalsIgnoreCase("help")) return;

        Messenger.sendMessage(sender, ChatColor.BOLD+"available commands");
        for(Permissions perm : Permissions.values())
            Messenger.sendMessage(sender, perm.getUsage()+" - "+perm.getDescription());
    }

}