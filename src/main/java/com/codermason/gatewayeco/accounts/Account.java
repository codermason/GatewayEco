package com.codermason.gatewayeco.accounts;

/**
 * created by codermason on 2/17/14
 */
public class Account implements Comparable<Account> {

    private String player;
    private int balance;
    private boolean receivePay;

    public Account(String player, int balance) {
        this.player = player;
        this.balance = balance;
        this.receivePay = false;
    }

    public String getPlayer() {
        return this.player;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean canReceivePay() {return this.receivePay;}

    public void togglePay() {
        this.receivePay = !this.receivePay;
    }

    public void setBalance(int r) {
        this.balance = r < 0 ? 0 : r;
    }

    public void addMoney(int r) {
        this.balance += r;
    }

    public void removeMoney(int r) {
        this.balance = hasAtLeast(r) ? this.balance - r : 0;
    }

    public void payAccount(Account other, int r) {
        other.addMoney(r);
        removeMoney(r);
    }

    public boolean hasAtLeast(int r) {
        return this.balance - r >= 0;
    }

    public int compareTo(Account other) {
        return getBalance() == other.getBalance() ? 0 : getBalance() - other.getBalance();
    }

}
