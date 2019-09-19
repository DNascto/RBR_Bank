package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.request.DepositRequest;
import com.tcs.RBRBankSpring.request.LoanRequest;
import com.tcs.RBRBankSpring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rbr/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public Account createAccount(Account conta) {
        return accountService.createAccount(conta);
    }


    @PostMapping("/loan")
    public ResponseEntity doLoan(@RequestBody LoanRequest loanRequest) {
        Account account = accountService.findByAccount(loanRequest.getAccountNumber());

        if (account != null) {
            if(accountService.createLoan(account, loanRequest.getValue()))
                ResponseEntity.ok().build();
            else
                ResponseEntity.status(HttpStatus.CONFLICT);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity doDeposit(@RequestBody DepositRequest deposit){
        Account account = accountService.findByAccount(deposit.getAccountNumber());

        if(account != null){
            accountService.doDeposit(account, deposit.getValue());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Account findByAccount(int accountNumber) {
        try {
            return accountService.findByAccount(accountNumber);
        }catch (Exception ex){
            System.out.println("Conta "+accountNumber+" nao encontrada.");
            ex.printStackTrace();
            return null;
        }
    }
}
