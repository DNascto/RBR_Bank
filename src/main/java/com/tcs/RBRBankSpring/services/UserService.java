package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;

import com.tcs.RBRBankSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountController accountController;

    public User createClient(User user){
        if(validateUser(user)) {
//            Account account = new AccountController().createAccount(user.getAccount().getAccountType());
//            AccountController accountController = new AccountController();
            Account account = accountController.createAccount(user.getAccount().getAccountType());
            user.setAccount(account);
            return userRepository.save(user);
        }else
            return null;
    }

    private boolean validateUser(User user) {
        boolean name = false;
        if(null == user.getName() || user.getName().isEmpty() || user.getName().matches("[0-9]*")
            || user.getName().length() < 6){
            return false;
        }else if(user.getName().matches("[a-zA-Z\\s]*")){
            name = true;
        }

        if(null == user.getCpf() || user.getCpf().isEmpty() || user.getCpf().matches("[a-zA-Z ]*")){
            return false;
        }

        if(null == user.getPassword() || user.getPassword().isEmpty()
                || user.getPassword().length() < 6 || user.getPassword().length() > 20){
            return false;
        }

        if(null == user.getBirthDate() || user.getBirthDate().toString().isEmpty()
                || user.getBirthDate().toString().matches("[a-zA-Z ]*")){
            return false;
        }

        if(null == user.getAccount() || user.getAccount().getAccountType().isEmpty()
                || user.getAccount().getLoanLimit() < 0){
            return false;
        }

        return name;
    }

    @Transactional(readOnly = true)
    public User validateLogin(String login, String password) {
        List<User> userList = userRepository.findAll();
        for (User u:userList) {
            if(u.getCpf().equals(login) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public User createLoan(User user) {
        return null;
    }

    @Transactional(readOnly = true)
    private void validateLoan(User user) {
    }

    public boolean createTransfer(User user, User addressee, Double value) {
        if(validateTransfer(user, addressee, value)){
            addressee.getAccount().setBalance(addressee.getAccount().getBalance() + value);
            user.getAccount().setBalance(user.getAccount().getBalance() - value);
            userRepository.save(addressee);
            userRepository.save(user);
        }
        return false;
    }

    @Transactional(readOnly = true)
    private boolean validateTransfer(User user, User addressee, Double value) {
        /** check if value is valide*/
        if(null == value || value <= 0 || value.isNaN()) {
            return false;
        }

        /** check if the addressee exist*/
        if(null == checkUserExistence(addressee.getAccount().getNumberAccount())){
            return false;
        }

        /** do the transfer if the client have money enough */
        if(user.getAccount().getBalance() >= value) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public User checkUserExistence(int numberAccount) {
        /** ACREDITE... isso funciona
         * int c = 768_890;
         * System.out.println("Seu cetico: " + c);*/
        return userRepository.findByAccount(numberAccount);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
