package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.controllers.LogTransactionsController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.TransactionType;
import com.tcs.RBRBankSpring.models.User;

import com.tcs.RBRBankSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {

    private UserRepository userRepository;
    private AccountController accountController;
    private LogTransactionsController logTransactionsController;

    @Autowired
    public UserService(UserRepository userRepository, AccountController accountController, LogTransactionsController logTransactionsController) {
        this.userRepository = userRepository;
        this.accountController = accountController;
        this.logTransactionsController = logTransactionsController;
    }

    public User createClient(User user){
        if(validateUser(user)) {
            Account account = accountController.createAccount(user.getAccount());
            user.setAccount(account);
            User newUser = userRepository.save(user);
//            logTransactionsController.newLog(TransactionType.NEWACCOUNT, newUser.getId(),
//                    "Criação do usuario com a conta: " + newUser.getAccount().getNumberAccount()+".");
            return newUser;
        }else
            return null;
    }

    private boolean validateUser(User user) {
        if(user.getCpf() == null || user.getCpf().isEmpty() || /*user.getCpf().matches("[a-zA-Z\\s]*")*/
            !user.getCpf().matches("[0-9]*") || user.getCpf().length() != 11){
            System.out.println("CPF invalido");
            return false;
        }

        if(user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().equals(user.getCpf())
                || user.getPassword().length() < 6 || user.getPassword().length() > 20){
            System.out.println("SENHA invalida");
            return false;
        }

        if(user.getBirthDate() == null || user.getBirthDate().toString().isEmpty()
                || user.getBirthDate().toString().matches("[a-zA-Z ]*")){
            System.out.println("DATA DE NASCIMENTO invalida");
            return false;
        }

        if(user.getAccount() == null || null == user.getAccount().getAccountType()
                || user.getAccount().getAccountType().isEmpty() || user.getAccount().getLoanLimit() < 0){
            System.out.println("CONTA invalida");
            return false;
        }

        if(user.getName() == null || user.getName().isEmpty() || user.getName().matches("[0-9]*")
                || user.getName().length() < 6){
            System.out.println("NOME incorreto");
            return false;
        }else if(user.getName().matches("[a-zA-Z\\s]*")){
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public User validateLogin(String login, String password) {
        List<User> userList = userRepository.findAll();
        try {
            for (User u : userList) {
                if (u.getCpf().equals(login) && u.getPassword().equals(password)) {
//                logTransactionsController.newLog(TransactionType.LOGIN, u.getId(),
//                        "Login realizado com sucesso pela conta: " + u.getAccount().getNumberAccount());
                    return u;
                }
            }
        }catch (Exception ex){
            System.out.println("USUARIO nao encontrado");
        }
        return null;
    }

//    public boolean createLoan(Account account, Double value) {
//        if(validateLoan(account, value)) {
//            account.setLoanLimit(account.getLoanLimit() - value);
//            User currentUser = userRepository.save(user);
//            logTransactionsController.newLog(TransactionType.LOAN, currentUser.getId(), "Emprestimo feito pelo usuario (id) "
//                    + currentUser.getId() + " com a conta \"" + currentUser.getAccount().getNumberAccount()+"\" no valor de ("+value+").");
//            return true;
//        }else
//            return false;
//    }
//
//    @Transactional(readOnly = true)
//    private boolean validateLoan(Account account, Double value) {
//        /* check if value is valide*/
//        if(null == value || value <= 0 || value.isNaN()) {
//            System.out.println("VALOR para EMPRESTIMO invalido");
//            return false;
//        }
//
//        if(account.getLoanLimit() < value) {
//            System.out.println("VALOR ultrapassa o limite de EMPRESTIMO");
//            return false;
//        }
//
//        return true;
//    }

//    @Transactional
//    public boolean createTransfer(User sender, User receiver, Double value) {
//        if(validateTransfer(sender, receiver, value)){
//            makeTransfer(sender, receiver, value);
//            return true;
//        }
//        System.out.println("DESTINATARIO nao encontrado");
//        return false;
//    }

//    private void makeTransfer(User sender, User receiver, Double value) {
//        receiver.getAccount().setBalance(receiver.getAccount().getBalance() + value);
//        userRepository.saveAndFlush(receiver);
//        sender.getAccount().setBalance(sender.getAccount().getBalance() - value);
//        userRepository.saveAndFlush(sender);
//        logTransactionsController.newLog(TransactionType.TRANSFER, sender.getId(), receiver.getId(),
//                "TRANSFERENCIA realizada por "+sender.getAccount().getNumberAccount()
//                        +" para "+receiver.getAccount().getNumberAccount());
//    }
//
//    @Transactional(readOnly = true)
//    private boolean validateTransfer(User user, User addressee, Double value) {
//        /* check if value is valide*/
//        if(null == value || value <= 0 || value.isNaN()) {
//            System.out.println("VALOR para TRANSFERENCIA invalido");
//            return false;
//        }
//
//        /* check if the addressee exist*/
//        if(null == checkUserExistence(addressee.getAccount().getNumberAccount())){
//            System.out.println("DESTINATARIO para TRANSFERENCIA nao encontrado");
//            return false;
//        }
//
//        /* do the transfer if the client have money enough */
//        if(user.getAccount().getBalance() >= value) {
//            return true;
//        }
//        return false;
//    }

//
//    public User checkUserExistence(int numberAccount) {
//        /* ACREDITE... isso funciona
//         * int c = 768_890;
//         * System.out.println("Seu cetico: " + c);*/
//        return userRepository.findByAccount(numberAccount);
//    }

//    TODO excluir esse metodo quando finalizar o projeto
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @Transactional(readOnly = true)
    public User findByAccount(int numberAccount) {
        return userRepository.findByAccount(numberAccount);
    }
}
