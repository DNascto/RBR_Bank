package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.models.Investment;
import com.tcs.RBRBankSpring.repositories.InvestmentRepository;
import com.tcs.RBRBankSpring.request.InvestmentRequest;
import com.tcs.RBRBankSpring.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rbr/investment")
@CrossOrigin(origins = "http://localhost:4200")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @PostMapping("/create")
    public ResponseEntity createInvestment(@RequestBody InvestmentRequest investment){
        if(investmentService.createInvestment(investment))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
