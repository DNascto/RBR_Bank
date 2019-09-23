package com.tcs.RBRBankSpring.response;

import com.tcs.RBRBankSpring.models.User;

public class ResponseUserWithToken {
    private User user;
    private String token;

    public ResponseUserWithToken(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
