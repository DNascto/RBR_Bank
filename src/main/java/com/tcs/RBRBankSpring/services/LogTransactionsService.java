package com.tcs.RBRBankSpring.services;

import com.tcs.RBRBankSpring.models.LogTransactions;
import com.tcs.RBRBankSpring.models.TransactionType;
import com.tcs.RBRBankSpring.repositories.LogTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogTransactionsService {
    @Autowired
    LogTransactionsRepository logTransactionsRepository;

    public void newLog(TransactionType type, Long idClient, String description) {
        LogTransactions log = new LogTransactions(new Date(), type, idClient, description);
        logTransactionsRepository.save(log);
    }

    public void newLog(TransactionType type, Long idClient, Long idAddressee, String description) {
        LogTransactions log = new LogTransactions(new Date(), type, idClient, idAddressee, description);
        logTransactionsRepository.save(log);
    }
}
