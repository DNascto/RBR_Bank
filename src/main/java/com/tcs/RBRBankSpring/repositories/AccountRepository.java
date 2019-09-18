package com.tcs.RBRBankSpring.repositories;

import com.tcs.RBRBankSpring.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.numberAccount = :acc")
    Account findByAccount(@Param("acc") int numberAccount);
}
