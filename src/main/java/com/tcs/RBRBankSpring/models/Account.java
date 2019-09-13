package com.tcs.RBRBankSpring.models;

public class Account {
    private Long id;
    private double balance;
    private double loanLimit;
    private String accountType;
    private String bankBranch;
    private int numberAccount;
    private User user;

    public Account() {
    }

    public Account(double balance, double loanLimit, String accountType, String bankBranch, int numberAccount, User user) {
        this.balance = balance;
        this.loanLimit = loanLimit;
        this.accountType = accountType;
        this.bankBranch = bankBranch;
        this.numberAccount = numberAccount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(int numberAccount) {
        this.numberAccount = numberAccount;
    }
}
