package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.repositories.UserRepository;
import com.tcs.RBRBankSpring.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Mock
    private AccountController accountController;

    @Mock
    private UserRepository userRepository;

//    @Mock
//    private LogTransactionsController logTransactionsController;

    @InjectMocks
    private UserService userService;

    private User user;
    private static List<User> userList = new ArrayList<>();


    @BeforeClass
    public static void setUp(){
        Account account = new Account();
        account.setAccountType("Corrente");
        userList.add(new User("Test Name","#p4ssW@rd","12345678901", new Date(), account));
        userList.add(new User("Test Name Second","_p4ssW@rd","12345678902", new Date(), account));
        userList.add(new User("Test Name Third","&p4ssW@rd","12345678903", new Date(), account));
    }

    @Before
    public void init() {
        when(accountController.createAccount(any()))
                .thenReturn(new Account());

        when(userRepository.findAll())
                .thenReturn(userList);
        user = new User("Test Name","#p4ssW@rd","12345678901", new Date(), new Account());
        user.getAccount().setAccountType("Corrente");
        user.getAccount().setNumberAccount(203040);
    }

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

    //@Ignore
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
        user.setCpf(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfVazio() {
        user.setCpf("");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComLetra() {
        user.setCpf("1l2e3t4r0a1");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComPonto() {
        user.setCpf("123.456.78901");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComCaracteres() {
        user.setCpf("123456789-01");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComMenosNumeros() {
        user.setCpf("1234567890");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComCpfComMaisNumeros() {
        user.setCpf("123456789012");

        Assert.assertNull(userService.createClient(user));
    }

    //@Ignore
    @Test
    public void assertLancaExcecaoAoCriarUsuarioComPassworErrado(){
        clienteComPasswordNulo();
        clienteComPasswordVazio();
        clienteComPasswordSemQuantidadeMinimaDeCaracteres();
        clienteComPasswordComQuantidadeMaximaExcedida();
        clienteComPasswordIgualAoCpf();
    }

    private void clienteComPasswordNulo() {
        user.setPassword(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordVazio() {
        user.setPassword("");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordSemQuantidadeMinimaDeCaracteres() {
        user.setPassword("12345");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordComQuantidadeMaximaExcedida() {
        user.setPassword("123456789012346578901");

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComPasswordIgualAoCpf() {
        user.setPassword("12345678901");

        Assert.assertNull(userService.createClient(user));
    }

    //@Ignore
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


    //@Ignore
    @Test
    public void assertLancaExcecaoAoCriarUsuarioComContaErrada() {
        clienteComContaNula();
//        clienteComContaComTipoNulo();
//        clienteComContaComTipoVazio();
    }

    private void clienteComContaNula() {
        user.setAccount(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComContaComTipoNulo() {
        user.getAccount().setAccountType(null);

        Assert.assertNull(userService.createClient(user));
    }

    private void clienteComContaComTipoVazio() {
        user.getAccount().setAccountType("");

        Assert.assertNull(userService.createClient(user));
    }

    @Test
    public void assertCriacaoDeUsuarioValido() {
        when(userRepository.save(user))
                .thenReturn(user);

        Assert.assertEquals(user, userService.createClient(user));
    }

    @Test
    public void assertErrosDeLogin(){
        loginSemUsuariosCadastrados();
        loginComUsuarioVazio();
        loginComSenhaVazio();
        loginComUsuarioIncorretoOuNaoEncontrado();
        loginComSenhaIncorreta();
    }

    @Test
    public void assertLoginDoCliente(){
        when(userRepository.findAll())
                .thenReturn(userList);

        Assert.assertEquals(userList.get(0), userService.validateLogin(user.getCpf(), user.getPassword()));
    }

    private void loginSemUsuariosCadastrados() {
        when(userRepository.findAll())
                .thenReturn(null);
        Assert.assertNull(userService.validateLogin("12345678901","p4ssW@rd"));
    }

    private void loginComUsuarioVazio() {
//        when(userRepository.findAll())
//                .thenReturn(userList);
        Assert.assertNull(userService.validateLogin("", "p4ssW@rd"));
    }

    private void loginComSenhaVazio() {
//        when(userRepository.findAll())
//                .thenReturn(userList);

        Assert.assertNull(userService.validateLogin("", "p4ssW@rd"));
    }

    private void loginComUsuarioIncorretoOuNaoEncontrado() {
        when(userRepository.findAll())
                .thenReturn(userList);

        Assert.assertNull(userService.validateLogin("12345678999", "p4ssW@rd"));
    }

    private void loginComSenhaIncorreta() {
        when(userRepository.findAll())
                .thenReturn(userList);

        Assert.assertNull(userService.validateLogin("12345678901", "passW@rd"));
    }

    @Test
    public void assertBuscaUsuario(){
        buscaUsuarioInexistente();
        buscaUsuarioComContaValida();
    }

    private void buscaUsuarioComContaValida(){
        when(userService.findByAccount(203040))
                .thenReturn(user);

        Assert.assertEquals(user, userService.findByAccount(user.getAccount().getNumberAccount()));
    }

    private void buscaUsuarioInexistente(){
        when(userService.findByAccount(147852))
                .thenReturn(user);

        Assert.assertNotEquals(user, userService.findByAccount(user.getAccount().getNumberAccount()));
    }

}
