package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import com.tcs.RBRBankSpring.repositories.LogTransactionsRepository;
import com.tcs.RBRBankSpring.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private LogTransactionsRepository logTransactionsRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @Before
    public void init(){
        account = new Account();
    }

    @Test
    public void assertCriacaoDeConta(){
        criarContaSemTipo();
        criarContaSemSaldoInicial();
        criarContaComSaldoInicial();
//        criarContaSemLimiteDeEmprestimo();
        criarContaComNumeroDeConta();
//        criarContaSem();
//        criarContaSem();

    }

    private void criarContaSemTipo() {
        Assert.assertNull(accountService.createAccount(account));
    }

    private void criarContaSemSaldoInicial() {
        accountService.createAccount(account);

        Assert.assertEquals(0, accountService.createAccount(account).getBalance(), 0.01);
    }

    private void criarContaComSaldoInicial() {
        account.setBalance(1234.56);
        accountService.createAccount(account);

        Assert.assertEquals(1234.56, accountService.createAccount(account).getBalance(), 0.01);
    }


//    private void criarContaSemLimiteDeEmprestimo() {
//        accountService.createAccount(account);
//    }

    private void criarContaComNumeroDeConta() {
        account.setBalance(1234.56);
        account.setNumberAccount(369852);
        account.setAccountType("Corrente");
        Account acc = accountService.createAccount(account);
        Assert.assertNotEquals(account.getNumberAccount(), acc.getNumberAccount());
    }
}
