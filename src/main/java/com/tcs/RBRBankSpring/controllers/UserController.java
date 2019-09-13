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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public User createUser(@RequestBody User user){
        if(userService.validateUser(user)) {
            Account account = new AccountController().createAccount();
            user.setAccount(account);
            return userRepository.save(user);
        }else
            return null;
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
        return userService.validateTransfer(user, addressee, value);
    }

    @PostMapping("/loan")
    public User doLoan(@RequestBody User user, @RequestBody Double value){
        userService.validateLoan(user);
        return userRepository.save(user);
    }
}
