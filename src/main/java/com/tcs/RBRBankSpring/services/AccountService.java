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

    public Account createAccount(String tipo) {
        Account account = new Account();
        account.setBalance(0);
        account.setAccountType(tipo);
        account.setBankBranch(174);
        account.setLoanLimit(500);
        account.setNumberAccount(generateAccountNumber());
        return accountRepository.save(account);
    }

    private int generateAccountNumber(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String vet[] = formatter.format(date).split("");
//        String dia = vet[0].replace("/", "");
//        String hr = vet[1].replace(":", "");
        int day = Integer.parseInt(vet[0].replace("/", ""));
        int hour = Integer.parseInt(vet[1].replace(":", ""));

        return day + hour;
    }
}
