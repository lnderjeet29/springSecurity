package com.example.springSecurity.services;

import com.example.springSecurity.dto.JwtAuthicationResponse;
import com.example.springSecurity.dto.RefreshToken;
import com.example.springSecurity.dto.SignUpRequest;
import com.example.springSecurity.dto.SigninRequest;
import com.example.springSecurity.entity.User;

public interface AuthentictionSerives {
    public User signup(SignUpRequest signUpRequest);
    JwtAuthicationResponse signin(SigninRequest signinRequest);
    JwtAuthicationResponse refreshToken(RefreshToken refreshToken);
}
