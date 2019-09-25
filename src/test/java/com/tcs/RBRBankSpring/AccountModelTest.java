package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.controllers.LogTransactionsController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import com.tcs.RBRBankSpring.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountModelTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private LogTransactionsController logTransactionsController;

    @InjectMocks
    private AccountService accountService;

//    @BeforeClass
//    public static void setUp(){
//        completeAccount = new Account(1234.56, 1000, "Corrente", 174, 20315426);
//    }
//
    private Account emptyAccount;

    @Before
    public void init(){
        emptyAccount = new Account();
        emptyAccount.setAccountType("Corrente");

//        doNothing().when(logTransactionsController).newLog(any(), anyLong(), anyString());
    }

    @Test
    public void criarContaSemTipo() {
        Account account = new Account(0, 1000, "",
                174, 20315426);

//        when(accountRepository.save(any()))
//                .thenReturn(null);

        Assert.assertNull(accountService.createAccount(account));
    }

    @Test
    public void criarContaSemSaldoInicial() {
        Account account = new Account(0, 0, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertEquals(0, accountService.createAccount(account).getBalance(), 0.01);
    }

    @Test
    public void criarContaComSaldoInicial() {
        Account account = new Account(1234.56, 1000, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertEquals(1234.56, accountService.createAccount(account).getBalance(), 0.01);
    }

    @Test
    public void criarContaSemLimiteDeEmprestimo() {
        Account account = new Account(0, 5000, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertEquals(5000, accountService.createAccount(emptyAccount).getLoanLimit(), 0.01);
    }

    @Test
    public void criarContaComLimiteDeEmprestimo() {
        Account account = new Account(0, 1000, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertEquals(account.getLoanLimit(), accountService.createAccount(account).getLoanLimit(), 0.01);
    }

//    @Test
//    public void criarContaComNumeroDeConta() {
//        Account account = new Account(0, 0, "Corrente",
//                174, 20315426);
//
//        when(accountRepository.save(any()))
//                .thenReturn(account);
//
//        Assert.assertNotEquals(account.getNumberAccount(), accountService.createAccount(account).getNumberAccount());
//    }

    @Test
    public void assertCriarCorretamenteUmaNovaConta(){
        Account account = new Account(0, 5000, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertEquals(account, accountService.createAccount(emptyAccount));
    }

            /** Testes de criação de emprestimo */

    @Test
    public void emprestimoComContaNula() {
        Assert.assertFalse(accountService.createLoan(null,1000d));
    }

//    @Test
//    public void emprestimoComContaInexistente() {
//        Account account = new Account(0, 5000, "Corrente",
//                174, 36985214);
//
//        when(accountRepository.save(any()))
//                .thenReturn(account);
//
//        Assert.assertFalse(accountService.createLoan(account,1000d));
//    }

    @Test
    public void emprestimoComValorNulo() {
        Account account = new Account(3000, 5000, "Corrente",
                174, 20315426);

        Assert.assertFalse(accountService.createLoan(account, -0_01D / 0x0_00));
    }

//    @Test
//    public void emprestimoComValorVazio() {
//        Account account = new Account(3000, 5000, "Corrente",
//                174, 20315426);
//
//        Assert.assertFalse(accountService.createLoan(account, Double.parseDouble("")));
//    }

    @Test
    public void emprestimoComValorNegativo() {
        Account account = new Account(3000, 5000, "Corrente",
                174, 20315426);

        Assert.assertFalse(accountService.createLoan(account, -1000d));
    }

    @Test
    public void emprestimoComValorAcimaDoLimitePermitido() {
        Account account = new Account(3000, 1000, "Corrente",
                174, 20315426);

        Assert.assertFalse(accountService.createLoan(account, 2000d));
    }

    @Test
    public void emprestimoSemSaldoSuficiente() {
        Account account = new Account(3000, 5000, "Corrente",
                174, 20315426);

        Assert.assertFalse(accountService.createLoan(account, 15000d));
    }

    @Test
    public void emprestimoComDadosValidos() {
        Account account = new Account(3000, 5000, "Corrente",
                174, 20315426);

        when(accountRepository.save(any()))
                .thenReturn(account);

        Assert.assertTrue(accountService.createLoan(account, 1000d));
    }

    /** Teste de criação de tranferencia */

    @Test
    public void transferenciaComContaDeDestinatarioNula(){
        Account account = new Account(3000, 5000, "Corrente",174, 20315426);
        Account accountReceiver = null;

        Assert.assertFalse(accountService.createTransfer(account, accountReceiver, 1000d));
    }
/*
    @Test
    public void transferenciaDestinatarioInexistente(){

    }

    @Test
    public void transferenciaContaInexistente(){

    }
    @Test
    public void transferenciaSemValor(){

    }
    @Test
    public void transferenciaComValorAcimaDoSaldo(){

    }
    @Test
    public void transferenciaComValorNegativo(){

    }*/
}
