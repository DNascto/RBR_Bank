package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.controllers.LogTransactionsController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.Investment;
import com.tcs.RBRBankSpring.models.TransactionType;
import com.tcs.RBRBankSpring.repositories.InvestmentRepository;
import com.tcs.RBRBankSpring.request.InvestmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;
    @Autowired
    private LogTransactionsController logTransactionsController;

    @Autowired
    private AccountController accountController;

    public boolean createInvestment(InvestmentRequest investment) {
        Account account = validateInvestment(investment);
        if(null != account) {
            Investment invest = new Investment();
            invest.setValue(investment.getValue());
            invest.setUserAccount(account);
            invest.setInvestmentType(investment.getInvestmentName());
            LocalDate data = LocalDate.now();
            switch (investment.getInvestmentName().toLowerCase()) {
                case "cdi": invest.setExpiry(data.plusYears(1));
                        invest.setProfitabilityRate(6F);
                        break;
                case "ipca": invest.setExpiry(data.plusYears(1));
                        invest.setProfitabilityRate(7F);
                        break;
                case "poupanca": invest.setExpiry(data.plusYears(100));
                        invest.setProfitabilityRate(4F);
                        break;
                default: return false;
            }
            investmentRepository.save(invest);
            logTransactionsController.newLog(TransactionType.INVESTMENT, account.getId(),
                    "Investimento "+invest.getInvestmentType()+" feito por "+account.getNumberAccount());
            return true;
        }
        return false;
    }

    private Account validateInvestment(InvestmentRequest investment) {
        if(investment.getValue() <= 0 || investment.getValue() < investment.getMinimumValue())
            return null;

        Account account = accountController.findByAccount(investment.getAccount().getNumberAccount());
        if(account.getBalance() < investment.getValue())
            return null;

        return account;
    }
}
