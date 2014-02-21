package com.codermason.gatewayeco;

import com.codermason.gatewayeco.accounts.Bank;
import com.codermason.gatewayeco.cmds.extra.CommandRouter;
import com.codermason.gatewayeco.config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * created by codermason on 2/16/14
 */
public class GatewayEco extends JavaPlugin {

    private static GatewayEco instance;
    private static Bank bank;
    private String[] cmds = {"pay", "togglepay", "money", "balance"};

    public enum ShutdownReason {CANNOT_LOAD_FILES;}

    public void onEnable() {
        instance = this;

        this.bank = new Bank(this);

        Config.init();
        getBank().openAccounts();
        registerCommands();

        log("Loaded "+getBank().getAccounts().size()+" different accounts!");

        this.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onJoin(PlayerJoinEvent e) {
                if(!getBank().hasAccount(e.getPlayer().getName()))
                    getBank().openAccount(e.getPlayer().getName(), 0);
            }

        }, this);
    }

    public void onDisable() {
        getBank().closeAccounts();
    }

    private void registerCommands() {
        for(String command : cmds)
            getCommand(command).setExecutor(new CommandRouter(this));
    }

    public static GatewayEco getInstance() {
        return instance;
    }

    public static Bank getBank() {
        return bank;
    }

    public static void log(String msg) {
        log(msg, Level.INFO);
    }

    public static void log(String msg, Level level) {
        getInstance().getLogger().log(level, msg);
    }

    public static void shutdown(ShutdownReason reason) {
        log(getInstance().getName()+" is shutting down! Reason: "+reason.name().toLowerCase().replace("_", " "));
        getInstance().getServer().getPluginManager().disablePlugin(getInstance());
    }

}
