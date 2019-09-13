package com.tcs.RBRBankSpring.models;

public class Investment {
    private Long id;
    private String investmentType;
    private User user;
//    private Account userAccount;
    private double value;

    public Investment() {
    }

    public Investment(String investmentType, User user, double value) {
        this.investmentType = investmentType;
        this.user = user;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
