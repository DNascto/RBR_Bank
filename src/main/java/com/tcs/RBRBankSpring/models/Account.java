package com.tcs.RBRBankSpring.models;

public class Account {
    private Long id;
    private double balance;
    private double loanLimit;
    private String accountType;
    private User user;

    public Account() {
    }

    public Account(Long id, double balance, double loanLimit, String accountType, User user) {
        this.id = id;
        this.balance = balance;
        this.loanLimit = loanLimit;
        this.accountType = accountType;
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
}
