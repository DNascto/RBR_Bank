package com.tcs.RBRBankSpring.repositories;

import com.tcs.RBRBankSpring.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
