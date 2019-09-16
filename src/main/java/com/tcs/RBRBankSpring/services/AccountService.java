package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String type) {
        Account account = new Account();
        account.setBalance(0);
        account.setAccountType(type);
        account.setBankBranch(174);
        account.setLoanLimit(500);
        account.setNumberAccount(generateAccountNumber());
        return accountRepository.save(account);
    }

    private int generateAccountNumber(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String vet = formatter.format(date).substring(0, 6) + formatter.format(date).substring(8, 10);
        String vet2 = formatter.format(date).substring(11);
        int day = Integer.parseInt(vet.replace("/", ""));
        int hour = Integer.parseInt(vet2.replace(":", ""));

        return day + hour;
    }
}
