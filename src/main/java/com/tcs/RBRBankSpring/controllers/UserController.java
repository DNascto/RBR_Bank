package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.repositories.UserRepository;
import com.tcs.RBRBankSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;

@RestController
@RequestMapping("/rbr/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public User createUser(@RequestBody User user){
        System.out.println("entrei: " + user.toString());
        return userService.createClient(user);
    }

    @GetMapping("/cli")
    public ResponseEntity<User> getClient(@RequestParam Integer numberAccount){
        return ResponseEntity.ok(userService.checkUserExistence(numberAccount));
    }

    @PostMapping("/login")
    public User loginAccount(@RequestBody String login, @RequestBody String password){
        return userService.validateLogin(login, password);
    }

    @PostMapping("/transfer")
    public boolean doTransfer(@RequestBody User user, @RequestBody User addressee, @RequestBody Double value){
        return userService.createTransfer(user, addressee, value);
    }

    @PostMapping("/loan")
    public User doLoan(@RequestBody User user, @RequestBody Double value){
        return userService.createLoan(user);
    }
}
