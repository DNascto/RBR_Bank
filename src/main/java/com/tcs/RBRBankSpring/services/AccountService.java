package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account acc) {
        Account account = new Account();
        account.setBalance(0);
        account.setAccountType(acc.getAccountType());
        account.setBankBranch(174);

        if(acc.getLoanLimit() > 0){
            account.setLoanLimit(acc.getLoanLimit());
        }else
            account.setLoanLimit(500);

        account.setNumberAccount(generateAccountNumber());
        return accountRepository.save(account);
    }

    private int generateAccountNumber(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String vet = formatter.format(date).substring(0, 6) + formatter.format(date).substring(8, 10);
        String vet2 = formatter.format(date).substring(11);
        int day = Integer.parseInt(vet.replace("/", ""));
        int hour = Integer.parseInt(vet2.replace(":", ""));

        return day + hour;
    }
}
