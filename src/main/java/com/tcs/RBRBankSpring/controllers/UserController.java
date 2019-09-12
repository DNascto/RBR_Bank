package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rbr")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/reg")
    public User createUser(@RequestBody User user){
        //service.validateUser() == true ? return userRepository.save(user) : return null;
        return userRepository.save(user);
    }

    @GetMapping("/cli")
    public User getClient(@RequestParam Long id){
        //service.checkUserExistence(id);
        return userRepository.findById(id).get();
    }

    @PostMapping("/transfer")
    public User doTransfer(@RequestBody User user, @RequestBody User addressee, @RequestBody Double value){
        //service.validateTransfer(user);
        return userRepository.save(user);
    }

    @PostMapping("/loan")
    public User doLoan(@RequestBody User user, @RequestBody Double value){
        //service.validateLoan(user);
        return userRepository.save(user);
    }
}
