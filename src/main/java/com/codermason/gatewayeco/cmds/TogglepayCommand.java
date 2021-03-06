package com.codermason.gatewayeco.cmds;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.accounts.Account;
import com.codermason.gatewayeco.cmds.extra.EcoCommand;
import com.codermason.gatewayeco.perms.Permissions;
import com.codermason.gatewayeco.utils.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * created by codermason on 2/20/14
 */
public class TogglepayCommand extends EcoCommand {

    public TogglepayCommand(GatewayEco plugin) {
        super(plugin);
    }

    public void execute(CommandSender sender, Command command, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("togglepay")) return;

        if(!Permissions.COMMANDS_TOGGLEPAY.validate(sender)) {
            Messenger.noPermissions(sender);
            return;
        }else if(!(sender instanceof Player)) {
            Messenger.noConsole();
            return;
        }

        Account account = super.getPlugin().getBank().getAccount(sender.getName());
        account.togglePay();
        Messenger.sendMessage(sender, "Your account "+(account.canReceivePay() ? "will" : "will not")+" receive payments!");
    }

}
