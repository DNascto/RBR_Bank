package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.models.Account;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AccountService {

    public Account createAccount(String tipo) {
        Account account = new Account();
        account.setBalance(0);
        account.setAccountType(tipo);
        account.setBankBranch(174);
        account.setLoanLimit(500);
        account.setNumberAccount(generateAccountNumber());

        return account;
    }
    private int generateAccountNumber(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DATE);
//        int hour = calendar.get(Calendar.);

        System.out.println(calendar.get(Calendar.DATE));
        return 000;
    }
}
