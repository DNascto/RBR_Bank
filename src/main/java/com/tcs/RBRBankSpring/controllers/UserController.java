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

    //TODO excluir esse metodo quando finalizar o projeto
    @GetMapping("/cli/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/cli")
    public ResponseEntity<User> getClient(@RequestParam Integer numberAccount) {
        return ResponseEntity.ok(userService.findByAccount(numberAccount));
    }

    public User loginAccount(@RequestBody User login) {
        return userService.validateLogin(login.getCpf(), login.getPassword());
    }
//
//    @PostMapping("/transfer")
//    public ResponseEntity doTransfer(@RequestBody TransferRequest transferRequest) {
//        User sender = userService.findByAccount(transferRequest.getSenderId());
//        User receiver = userService.findByAccount(transferRequest.getReceiverId());
//
//        if (sender != null && receiver != null) {
//            if(userService.createTransfer(sender, receiver, transferRequest.getValue()))
//                return ResponseEntity.ok().build();
//            else
//                ResponseEntity.status(HttpStatus.CONFLICT);
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PostMapping("/loan")
//    public ResponseEntity doLoan(@RequestBody LoanRequest loanRequest) {
//        User user = userService.findByAccount(loanRequest.getAccountNumber());
//
//        if (user != null) {
//            if(userService.createLoan(user.getAccount(), loanRequest.getValue()))
//                ResponseEntity.ok().build();
//            else
//                ResponseEntity.status(HttpStatus.CONFLICT);
//        }
//        return ResponseEntity.notFound().build();
//    }

    public User findByAccount(int numberAccount) {
        return userService.findByAccount(numberAccount);
    }
}
