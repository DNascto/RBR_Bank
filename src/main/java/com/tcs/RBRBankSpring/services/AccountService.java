package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.controllers.LogTransactionsController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.TransactionType;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LogTransactionsController logTransactionsController;

    public Account createAccount(Account acc) {
        Account account = new Account();
        //TODO remover quando finalizar a regra de negocio
        account.setAccountType(acc.getAccountType());
        account.setBankBranch(174);

        account.setBalance(acc.getBalance() > 0 ? acc.getBalance() : 0d);

        account.setLoanLimit(acc.getLoanLimit() > 0 ? acc.getLoanLimit() : 5000d);

        account.setNumberAccount(generateAccountNumber());
        return accountRepository.save(account);
    }

    private int generateAccountNumber(){
        Instant date = Instant.now();
        String part1 = date.toString().substring(0, 10);
        String vet2 = date.toString().substring(11,19);
        int day = Integer.parseInt(part1.replace("-", ""));
        int hour = Integer.parseInt(vet2.replace(":", ""));

        return day + hour;
    }

    public boolean createLoan(Account account, Double value) {
        if(validateLoan(account, value)) {
            account.setLoanLimit(account.getLoanLimit() - value);
            account.setBalance(account.getBalance() + value);
            Account currentAccount = accountRepository.save(account);
            logTransactionsController.newLog(TransactionType.LOAN, currentAccount.getId(),
                    "Emprestimo feito pelo usuario (id) " + currentAccount.getId() +
                            " com a conta \"" + currentAccount.getNumberAccount() + "\" no valor de (" + value + ").");
            return true;
        }else
            return false;
    }

    @Transactional(readOnly = true)
    private boolean validateLoan(Account account, Double value) {
        /* check if value is valide*/
        if(null == value || value <= 0 || value.isNaN()) {
            System.out.println("VALOR para EMPRESTIMO invalido");
            return false;
        }

        if(account.getLoanLimit() < value) {
            System.out.println("VALOR ultrapassa o limite de EMPRESTIMO");
            return false;
        }

        return true;
    }

    public Account findByAccount(int accountNumber) {
        return accountRepository.findByAccount(accountNumber);
    }
}
