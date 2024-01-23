package com.example.springSecurity.controller;

import com.example.springSecurity.dto.JwtAuthicationResponse;
import com.example.springSecurity.dto.RefreshToken;
import com.example.springSecurity.dto.SignUpRequest;
import com.example.springSecurity.dto.SigninRequest;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.services.AuthentictionSerives;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentictionSerives authentictionSerives;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authentictionSerives.signup(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthicationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authentictionSerives.signin(signinRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthicationResponse> signin(@RequestBody RefreshToken Request){
        return ResponseEntity.ok(authentictionSerives.refreshToken(Request));
    }
}
