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

		userRepository.save(new User("eu de deus", "123456798", "12345678901", new Date(),
			accountRepository.save(new Account(123, 100, "poupanca", 1234, 123456))));
	}
}
