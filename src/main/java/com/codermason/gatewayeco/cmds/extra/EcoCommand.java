package com.codermason.gatewayeco.cmds.extra;

import com.codermason.gatewayeco.GatewayEco;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/20/14
 */
public abstract class EcoCommand {

    private GatewayEco plugin;

    public EcoCommand(GatewayEco plugin) {
        this.plugin = plugin;
    }

    public GatewayEco getPlugin() {return this.plugin;}

    public abstract void execute(CommandSender sender, Command command, String[] args);

}
