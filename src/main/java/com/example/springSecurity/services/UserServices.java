package com.example.springSecurity.services;

import com.example.springSecurity.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices {
    public UserDetailsService userDetailsService();
    public User findUser(String username);
}
