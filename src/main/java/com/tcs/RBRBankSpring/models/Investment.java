package com.tcs.RBRBankSpring.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String investmentType;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="user", referencedColumnName="id")
//    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account userAccount;
    private double value;
    private float profitabilityRate;
    private LocalDate expiry;

    public Investment() {
    }

    public Investment(String investmentType, Account userAccount, double value, float profitabilityRate, LocalDate expiry) {
        this.investmentType = investmentType;
        this.userAccount = userAccount;
        this.value = value;
        this.profitabilityRate = profitabilityRate;
        this.expiry = expiry;
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

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public float getProfitabilityRate() {
        return profitabilityRate;
    }

    public void setProfitabilityRate(float profitabilityRate) {
        this.profitabilityRate = profitabilityRate;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }
}
