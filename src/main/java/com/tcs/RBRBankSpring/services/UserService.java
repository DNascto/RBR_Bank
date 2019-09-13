package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(User user) {
        boolean name = false;
        if(null == user.getName() || user.getName().isEmpty() || user.getName().matches("[0..9]")
            || user.getName().length() < 6){
            return false;
        }else if(user.getName().matches("[a..zA..Z]")){
            name = true;
        }

        if(null == user.getCpf() || user.getCpf().isEmpty() || user.getCpf().matches("[a..zA..Z]")){
            return false;
        }

        if(null == user.getPassword() || user.getPassword().isEmpty()
                || user.getPassword().length() < 6 || user.getPassword().length() > 20){
            return false;
        }

        if(null == user.getBirthDate() || user.getBirthDate().toString().isEmpty()
                || user.getBirthDate().toString().matches("[a..zA..Z]")){
            return false;
        }

        if(null == user.getAccount() || user.getAccount().getAccountType().isEmpty()
                || user.getAccount().getLoanLimit() < 0){
            return false;
        }

        if(name) {
            return true;
        }
        return false;
    }

    public User checkUserExistence(int numberAccount) {
        List<User> userList = userRepository.findAll();
        for (User u:userList) {
            if(u.getAccount().getNumberAccount() == numberAccount){
                return u;
            }
        }
        return null;
    }

    public User validateLogin(String login, String password) {
        List<User> userList = userRepository.findAll();
        for (User u:userList) {
            if(u.getCpf().equals(login) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public void validateLoan(User user) {

    }

    public boolean validateTransfer(User user, User addressee, Double value) {
        if(null == value || value <= 0 || value.isNaN()) {
            return false;
        }

        /** check if the addressee exist*/
        if(null == checkUserExistence(addressee.getAccount().getNumberAccount())){
            return false;
        }

        /** do the transfer if the client have money enough*/
        if(user.getAccount().getBalance() >= value) {
            addressee.getAccount().setBalance(addressee.getAccount().getBalance() + value);
            user.getAccount().setBalance(user.getAccount().getBalance() - value);
            userRepository.save(addressee);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
