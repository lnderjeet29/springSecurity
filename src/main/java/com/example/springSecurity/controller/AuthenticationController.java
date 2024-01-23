package com.example.springSecurity.controller;

import com.example.springSecurity.dto.*;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.services.AuthentictionSerives;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
//        return ResponseEntity.ok(authentictionSerives.signup(signUpRequest));
        return new ResponseEntity<User>(authentictionSerives.signup(signUpRequest), HttpStatus.ACCEPTED);
    }
    @PostMapping("/signin")
    public ResponseEntity<ApiRepositoryMesssage> signin(@RequestBody SigninRequest signinRequest){

        try {
            JwtAuthicationResponse response=authentictionSerives.signin(signinRequest);
            ApiRepositoryMesssage messsage=ApiRepositoryMesssage.builder().response(response).message("Login successfully..").status(true)
                    .build();
            return new ResponseEntity<>(messsage,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            ApiRepositoryMesssage message=ApiRepositoryMesssage.builder().message("Oops!! User not found...").status(false)
                    .build();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthicationResponse> refresh(@RequestBody RefreshToken Request){
        return ResponseEntity.ok(authentictionSerives.refreshToken(Request));
    }
}
