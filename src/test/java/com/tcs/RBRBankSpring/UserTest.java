package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.controllers.UserController;
import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

public class UserTest {

    @Autowired
    private UserController userController;

    @Test
    public void assertUserAccountCreation(){
        //clienteSemNome();
    }

    private void clienteSemNome()throws NullPointerException{
        User user = new User();
        user.setName(null);
        user.setCpf("12345678901");
        user.setBirthDate(new Date()/*LocalDate.of(2000, 1, 15)*/);
        user.setPassword("123456789");
        user.setAccount(new Account());
        user.getAccount().setAccountType("corrente");

//        new AssertionError(userController.createUser(user));

    }

    private void clienteComNumerosNome(){

    }

    private void clienteComCaracterEspecialNoNome(){

    }

}
