package com.tcs.RBRBankSpring;

import com.tcs.RBRBankSpring.controllers.AccountController;
import com.tcs.RBRBankSpring.repositories.AccountRepository;
import com.tcs.RBRBankSpring.services.AccountService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;



}
