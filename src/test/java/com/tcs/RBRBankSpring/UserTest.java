package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.services.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class UserTest {

//    @Autowired
//    private UserController userController;

    private UserService userService = new UserService();;

    @Test
    public void assertLancaExcecaoAoCriarUsuarioComNomeErrado() {
        clienteSemNome();
        clienteComNomeNulo();
        clienteComNumerosNome();
        clienteComCaracterEspecialNoNome();
    }

    private void clienteComNomeNulo() {
        User user = new User();
        user.setCpf("12345678901");
        user.setBirthDate(new Date()/*LocalDate.of(2000, 1, 15)*/);
        user.setPassword("123456789");
        user.setAccount(new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteSemNome() {
        User user = new User();
        user.setName("");
        user.setCpf("12345678901");
        user.setBirthDate(new Date()/*LocalDate.of(2000, 1, 15)*/);
        user.setPassword("123456789");
        user.setAccount(new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComNumerosNome(){
        User user = new User("Teste 123","p4ssW@rd","12345678901",new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCaracterEspecialNoNome(){
        User user = new User("Test & Testers","p4ssW@rd","12345678901",new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    @Test
    public void assertLancaExcecaoAoCriarUsuarioComCpfErrado(){
        clienteComCpfNulo();
        clienteComCpfVazio();
        clienteComCpfComLetra();
        clienteComCpfComPonto();
        clienteComCpfComCaracteres();
        clienteComCpfComMenosNumeros();
        clienteComCpfComMaisNumeros();
    }

    private void clienteComCpfNulo() {
        User user = new User("Test Name","p4ssW@rd",null, new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfVazio() {
        User user = new User("Test Name","p4ssW@rd","", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComLetra() {
        User user = new User("Test Name","p4ssW@rd","1l2e3t4r0a1", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComPonto() {
        User user = new User("Test Name","p4ssW@rd","123456789.1", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComCaracteres() {
        User user = new User("Test Name","p4ssW@rd","123456789-01", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComMenosNumeros() {
        User user = new User("Test Name","p4ssW@rd","1234567890", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComMaisNumeros() {
        User user = new User("Test Name","p4ssW@rd","123456789012", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    @Test
    public void assertLancaExcecaoAoCriarUsuarioComPassworErrado(){
        clienteComPasswordNulo();
        clienteComPasswordVazio();
        clienteComPasswordSemQuantidadeMinimaDeCaracteres();
        clienteComPasswordComQuantidadeMaximaExcedida();
        clienteComPasswordIgualAoCpf();
    }

    private void clienteComPasswordNulo() {
        User user = new User("Test Name",null,"12345678901", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordVazio() {
        User user = new User("Test Name","","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordSemQuantidadeMinimaDeCaracteres() {
        User user = new User("Test Name","12345","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordComQuantidadeMaximaExcedida() {
        User user = new User("Test Name","123456789012346578901","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordIgualAoCpf() {
        User user = new User("Test Name","12345678901","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }


    @Test
    public void assertLancaExcecaoAoCriarUsuarioComDataDeNascimentoErrada() throws ParseException {
        clienteComDataDeNascimentoNula();
//        clienteComDataDeNascimentoVazia();
//        clienteComDataDeNascimentoComDiaAtual();
//        clienteComDataDeNascimentoComDataInexistente();
    }

    private void clienteComDataDeNascimentoNula() {
        User user = new User("Test Name","p4ssW@rd","12345678901", null, new Account());
        user.getAccount().setAccountType("corrente");

        Assert.assertNull(userService.createClient(user));
    }

//    private void clienteComDataDeNascimentoVazia() throws ParseException {
//        Date date = new SimpleDateFormat( "yyyyMMdd" ).parse( "        ");
//        User user = new User("Test Name","p4ssW@rd","12345678901", date, new Account());
//        user.getAccount().setAccountType("corrente");
//
//        Assert.assertNull(userService.createClient(user));
//    }

    @Test
    public void assertLancaExcecaoAoCriarUsuarioComContaErrada() {
        clienteComContaNula();
        clienteComContaComTipoNulo();
        clienteComContaComTipoVazio();
//        clienteComConta();
//        clienteComConta();
    }

    private void clienteComContaNula() {
        User user = new User("Test Name","p4ssW@rd","12345678901", new Date(), new Account());
        user.setAccount(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComContaComTipoNulo() {
        User user = new User("Test Name","p4ssW@rd","12345678901", new Date(), new Account());
        user.getAccount().setAccountType(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComContaComTipoVazio() {
        User user = new User("Test Name","p4ssW@rd","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("");

        Assert.assertNull(userService.createClient(user));
    }

}
