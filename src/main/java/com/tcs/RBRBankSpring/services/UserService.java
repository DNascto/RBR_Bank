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
        if(user.getCpf() == null || user.getCpf().isEmpty() || user.getCpf().startsWith("0") ||
                /*user.getCpf().matches("[a-zA-Z\\s]*")*/
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
    @Transactional(readOnly = true)
    public User getLogin(String login) {
        List<User> userList = userRepository.findAll();
        try {
            for (User u : userList) {
                if (u.getCpf().equals(login)) {
                    return u;
                }
            }
        }catch (Exception ex){
            System.out.println("USUARIO nao encontrado");
        }
        return null;
    }
//    TODO excluir esse metodo quando finalizar o projeto
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByAccount(int numberAccount) {
        return userRepository.findByAccount(numberAccount);
    }
}
