package com.example.springSecurity.controller;

import com.example.springSecurity.dto.*;
import com.example.springSecurity.entity.User;
import com.example.springSecurity.services.AuthentictionSerives;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthentictionSerives authentictionSerives;
    @PostMapping("/signup")
    public ResponseEntity<ApiUserMessage> signup(@RequestBody SignUpRequest signUpRequest){

        try{
            User user=authentictionSerives.signup(signUpRequest);
            ApiUserMessage message=ApiUserMessage.builder().response(user).message("user sign up successfully...").status(true).build();
            return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
        }catch (Exception e){
            ApiUserMessage message=ApiUserMessage.builder().message("credential false...").status(false).build();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<ApiRepositoryMesssage> signin(@RequestBody SigninRequest signinRequest){

        try {
            JwtAuthicationResponse response=authentictionSerives.signin(signinRequest);
            ApiRepositoryMesssage messsage;
            if (response.isStatus()){
            messsage=ApiRepositoryMesssage.builder().response(response).message("Login successfully..").status(true)
                    .build();
                return new ResponseEntity<>(messsage,HttpStatus.ACCEPTED);
            }else{
                messsage=ApiRepositoryMesssage.builder().message("Oops!! User not found...").status(false)
                        .build();
                return new ResponseEntity<>(messsage,HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            ApiRepositoryMesssage message=ApiRepositoryMesssage.builder().message("Oops!! User not found...").status(false)
                    .build();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/refresh")
    public ResponseEntity<ApiRepositoryMesssage> refresh(@RequestHeader(name = "Authorization") String Request){

        try{
            JwtAuthicationResponse response= authentictionSerives.refreshToken(Request.substring(7));
            return new ResponseEntity<>(ApiRepositoryMesssage.builder().response(response).status(true).message("successful...")
                    .build(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(ApiRepositoryMesssage.builder().message("failed to generate token...").status(false).build(),HttpStatus.BAD_REQUEST);
        }
    }
}
