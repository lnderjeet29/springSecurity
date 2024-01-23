package com.example.springSecurity.services.impl;

import com.example.springSecurity.Repo.UserRepository;
import com.example.springSecurity.dto.JwtAuthicationResponse;
import com.example.springSecurity.dto.RefreshToken;
import com.example.springSecurity.dto.SignUpRequest;
import com.example.springSecurity.dto.SigninRequest;
import com.example.springSecurity.entity.Role;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.exception.BadApiRequest;
import com.example.springSecurity.services.AuthentictionSerives;
import com.example.springSecurity.services.JWTServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthentictionSerivesImpl implements AuthentictionSerives {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTServices jwtServices;
    private final AuthenticationManager authenticationManager;
    public User signup(SignUpRequest signUpRequest){
        User user=new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUserName(signUpRequest.getUsername());
        user.setPhone_number("709839987");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthicationResponse signin(SigninRequest signinRequest){
        JwtAuthicationResponse jwtAuthicationResponse= null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                    signinRequest.getPassword()));
            var user=userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()->new BadApiRequest("Invalid email or password"));
            var jwt=jwtServices.generateToken(user);
            var refreshToken=jwtServices.generateRefreshToken(new HashMap<>(),user);
            jwtAuthicationResponse = new JwtAuthicationResponse();
            jwtAuthicationResponse.setToken(jwt);
            jwtAuthicationResponse.setEmail(user.email);
            jwtAuthicationResponse.setRefreshToken(refreshToken);
            jwtAuthicationResponse.setStatus(true);
        } catch (Exception e) {
            jwtAuthicationResponse = new JwtAuthicationResponse();
            jwtAuthicationResponse.setStatus(false);
        }
        return jwtAuthicationResponse;
    }
    public JwtAuthicationResponse refreshToken(String refreshToken){
        String userEmail=jwtServices.extractUserName(refreshToken);
        User user=userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtServices.isTokenValid(refreshToken,user)){
            var jwt=jwtServices.generateToken(user);
            JwtAuthicationResponse jwtAuthicationResponse=new JwtAuthicationResponse();
            jwtAuthicationResponse.setToken(jwt);
            jwtAuthicationResponse.setEmail(userEmail);
            jwtAuthicationResponse.setRefreshToken(refreshToken);
            return jwtAuthicationResponse;
        }
        return null;
    }
}

