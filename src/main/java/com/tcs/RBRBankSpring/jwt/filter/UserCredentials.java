package com.tcs.RBRBankSpring.jwt.filter;

import lombok.Data;

@Data
public class UserCredentials {
    private String name;
    private String password;
}
