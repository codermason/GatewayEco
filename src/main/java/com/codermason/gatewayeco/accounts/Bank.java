package com.codermason.gatewayeco.accounts;

import com.codermason.gatewayeco.GatewayEco;
import com.codermason.gatewayeco.config.Config;

import javax.security.auth.login.AccountException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * created by codermason on 2/17/14
 */
public class Bank {

    private List<Account> accounts = new ArrayList<Account>();
    private GatewayEco plugin;

    public Bank(GatewayEco plugin) {
        this.plugin = plugin;
    }

    public Account openAccount(String player, int balance) {
        if(hasAccount(player)) return getAccount(player);
        Account account = new Account(player, balance);
        accounts.add(account);
        return account;
    }

    public void openAccounts() {
        for(String player : Config.getPlayersConfig().getKeys(false))
            accounts.add(new Account(player, Config.getBalance(player)));
    }

    public void closeAccounts() {
        for(Account account : accounts)
            Config.updateAccount(account);
    }

    public Account getAccount(String player) {
        for(Account account : accounts)
            if(account.getPlayer().equals(player))
                return account;
        Account account = new Account(player, 0);
        accounts.add(account);
        return account;
    }

    public boolean hasAccount(String player) {
        return getAccount(player) != null;
    }

    public void sortAccounts() {
        Collections.sort(accounts, new Comparator<Account>() {
            public int compare(Account one, Account two) {
                return one.getBalance() - two.getBalance();
            }
        });
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

}
