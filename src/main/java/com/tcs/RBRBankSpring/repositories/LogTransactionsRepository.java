package com.tcs.RBRBankSpring.repositories;

import com.tcs.RBRBankSpring.models.LogTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTransactionsRepository extends JpaRepository<LogTransactions, Long> {

}
