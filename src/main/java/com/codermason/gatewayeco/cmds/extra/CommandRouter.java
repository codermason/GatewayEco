package com.codermason.gatewayeco.cmds.extra;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.cmds.BalanceCommand;
import com.codermason.gatewayeco.cmds.PayCommand;
import com.codermason.gatewayeco.cmds.TogglepayCommand;
import com.codermason.gatewayeco.cmds.moneycmds.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/20/14
 */
public class CommandRouter implements CommandExecutor {

    private GatewayEco plugin;

    public CommandRouter(GatewayEco plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getLabel().equalsIgnoreCase("money")) {

            if(args.length <= 0)
                new BalanceCommand(plugin).execute(sender, command, args);
            else if(args[0].equalsIgnoreCase("set"))
                new SetCommand(plugin).execute(sender, command, args);
            else if(args[0].equalsIgnoreCase("give"))
                new GiveCommand(plugin).execute(sender, command, args);
            else if(args[0].equalsIgnoreCase("take"))
                new TakeCommand(plugin).execute(sender, command, args);
            else if(args[0].equalsIgnoreCase("top"))
                new TopCommand(plugin).execute(sender, command, args);
            else if(args[0].equalsIgnoreCase("help"))
                new HelpCommand(plugin).execute(sender, command, args);
            //my IDE runs on java 6, cant use switch statements with string objects :/

        }else if(command.getLabel().equalsIgnoreCase("pay"))
            new PayCommand(plugin).execute(sender, command, args);
        else if(command.getLabel().equalsIgnoreCase("togglepay"))
            new TogglepayCommand(plugin).execute(sender, command, args);
        else if(command.getLabel().equalsIgnoreCase("balance"))
            new BalanceCommand(plugin).execute(sender, command, args);
        return false;
    }

}
