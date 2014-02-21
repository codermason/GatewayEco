package com.codermason.gatewayeco.cmds.moneycmds;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.accounts.Account;
import com.codermason.gatewayeco.cmds.extra.EcoCommand;
import com.codermason.gatewayeco.perms.Permissions;
import com.codermason.gatewayeco.utils.Messenger;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * created by codermason on 2/20/14
 */
public class TopCommand extends EcoCommand {

    private int PAGE_LENGTH = 7;

    public TopCommand(GatewayEco plugin) {
        super(plugin);
    }

    public void execute(CommandSender sender, Command command, String[] args) {
        if(!args[0].equalsIgnoreCase("top")) return;

        if(!Permissions.COMMANDS_MONEY_TOP.validate(sender)) {
            Messenger.noPermissions(sender);
            return;
        }

        int pageAmount = (int) Math.ceil((double)super.getPlugin().getBank().getAccounts().size()/PAGE_LENGTH);

        if(args.length == 1) {
            sendPage(sender, 1);
        }else if(args.length == 2) {
            String s = args[1];
            if(NumberUtils.isNumber(s)) {
                int page = Integer.parseInt(s);
                if(page >= 1 && page <= pageAmount) {
                    sendPage(sender, page);
                }else{
                    Messenger.sendMessage(sender, "Page must be between 1 and "+pageAmount+"!");
                }
            }else{
                Messenger.sendMessage(sender, "Integer expected; string received");
            }
        }else{
            Messenger.usage(sender, Permissions.COMMANDS_MONEY_TOP.getUsage());
        }
    }

    public void sendPage(CommandSender sender, int page) {
        Messenger.sendMessage(sender, ChatColor.BOLD+"richest players #"+page);
        --page;
        int fromIndex = page*PAGE_LENGTH, toIndex = (fromIndex+PAGE_LENGTH)-1, listSize = super.getPlugin().getBank().getAccounts().size();
        List<Account> copy = super.getPlugin().getBank().getAccounts().subList(fromIndex, toIndex >= listSize ? listSize-- : toIndex);
        for(int x=0;x<copy.size();x++)
            Messenger.sendMessage(sender, (fromIndex+x+1)+". "+copy.get(x).getPlayer()+" ($"+copy.get(x).getBalance()+")");
    }

}
