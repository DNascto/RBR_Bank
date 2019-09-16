package com.tcs.RBRBankSpring.controllers;

import com.tcs.RBRBankSpring.models.TransactionType;
import com.tcs.RBRBankSpring.services.LogTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rbr/log")
@CrossOrigin(origins = "http://localhost:4200")
public class LogTransactionsController {
    @Autowired
    LogTransactionsService logTransactionsService;

    public void newLog(TransactionType type, Long idClient, String description){
        logTransactionsService.newLog(type, idClient, description);
    }
    public void newLog(TransactionType type, Long idClient, Long idAddressee, String description){
        logTransactionsService.newLog(type, idClient, idAddressee, description);
    }
}
