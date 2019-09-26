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

import java.util.Date;
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
            logTransactionsController.newLog(TransactionType.NEWACCOUNT, newUser.getId(),
                    "Criação do usuario com a conta: " + newUser.getAccount().getNumberAccount()+".");
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
        if(!validateCpf(user.getCpf()))
            return false;

        if(user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().equals(user.getCpf())
                || user.getPassword().length() < 6 || user.getPassword().length() > 20){
            System.out.println("SENHA invalida");
            return false;
        }

        if(user.getBirthDate() == null || user.getBirthDate().toString().isEmpty()
                || user.getBirthDate().toString().matches("[a-zA-Z ]*") || user.getBirthDate().compareTo(new Date()) >= 0){
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

    public boolean validateCpf(String cpf) {
        String[] str = cpf.split("");

        if(cpf.matches("[0]{11}|[1]{11}|[2]{11}|[3]{11}|[4]{11}|[5]{11}|[6]{11}|[7]{11}|[8]{11}|[9]{11}")) {
            return false;
        }

        int[] nums = new int[11];

        for (int a = 0; a < str.length; a++) {
            nums[a] = Integer.parseInt(str[a]);
        }

        if(somatorio(nums, 10) != nums[9])
            return false;

        if(somatorio(nums, 11) != nums[10])
            return false;

        return true;
    }

    private int somatorio(int str[], int init){
        int soma = 0;
        for (int i = init, j=0; i > 1; i--, j++){
            soma += str[j] * i;
        }
        int res = (soma * 10) % 11;
        return res == 10 ? 0 : res ;
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
