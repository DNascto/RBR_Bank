package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import com.tcs.RBRBankSpring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class RbrBankSpringApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(RbrBankSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(new User("RBR Banking", "RB5b@nk1ng", "10101010111", new Date(),
			accountRepository.save(new Account(1000000, 1000000000,
					"Bancaria", 174, 000001))));
	}
}
