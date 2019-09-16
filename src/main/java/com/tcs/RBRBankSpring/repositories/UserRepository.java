package com.tcs.RBRBankSpring.repositories;

import com.tcs.RBRBankSpring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u LEFT JOIN FETCH u.account WHERE u.account.numberAccount = :acc")
//    User findByAccount(@Param("acc") int numberAccount);
}
