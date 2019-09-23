package com.tcs.RBRBankSpring.JWT.service;

import com.tcs.RBRBankSpring.JWT.config.WebSecurityConfig;
import com.tcs.RBRBankSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    WebSecurityConfig webSecurityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.tcs.RBRBankSpring.models.User user = userService.getLogin(username);


        if(user != null) {
            return new User(user.getCpf(), webSecurityConfig.passwordEncoder().encode(user.getPassword()), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not Found");
        }
    }
}
