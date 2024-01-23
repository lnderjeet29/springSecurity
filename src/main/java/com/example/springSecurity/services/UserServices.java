package com.example.springSecurity.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices {
    public UserDetailsService userDetailsService();
}
