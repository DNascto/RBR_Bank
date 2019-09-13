package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.models.Account;
import com.tcs.RBRBankSpring.models.User;
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

	public static void main(String[] args) {
		SpringApplication.run(RbrBankSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//userRepository.save(new User("eu", "123456798", "123456789", new Date(), new Account()));
	}
}
