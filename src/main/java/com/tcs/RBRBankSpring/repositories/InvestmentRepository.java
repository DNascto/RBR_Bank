package com.tcs.RBRBankSpring.repositories;

import com.tcs.RBRBankSpring.models.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
