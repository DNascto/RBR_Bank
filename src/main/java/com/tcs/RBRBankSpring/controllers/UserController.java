package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.request.LoanRequest;
import com.tcs.RBRBankSpring.request.TransferRequest;
import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rbr/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public User createUser(@RequestBody User user) {
        return userService.createClient(user);
    }

//    @GetMapping("/cli/all")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping("/cli")
    public ResponseEntity<User> getClient(@RequestParam Integer numberAccount) {
        return ResponseEntity.ok(userService.findByAccount(numberAccount));
    }

    public User loginAccount(@RequestBody User login) {
        return userService.validateLogin(login.getCpf(), login.getPassword());
    }

    public User findByAccount(int numberAccount) {
        return userService.findByAccount(numberAccount);
    }

    @GetMapping("/cpf")
    public boolean validateCpf(@RequestParam String cpf) {
        return userService.validateCpf(cpf);
    }
}
