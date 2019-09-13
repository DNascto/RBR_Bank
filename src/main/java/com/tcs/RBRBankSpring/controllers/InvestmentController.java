package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.repositories.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rbr/investment")
@CrossOrigin(origins = "http://localhost:4200")
public class InvestmentController {
    @Autowired
    private InvestmentRepository investmentRepository;
}
