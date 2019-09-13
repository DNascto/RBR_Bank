package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rbr/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
}
