package com.example.springSecurity.controller;

import com.example.springSecurity.dto.ApiUserMessage;
import com.example.springSecurity.services.JWTServices;
import com.example.springSecurity.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JWTServices Jwt;
    private final UserServices userServices;
    Logger logger=  LoggerFactory.getLogger(UserController.class);
    @GetMapping("/info")
    public ResponseEntity<ApiUserMessage> user(@RequestHeader(name = "Authorization") String token){
        logger.info(token);
        String username= null;
        try {
            username = Jwt.extractUserName(token.substring(7));
            ApiUserMessage response=ApiUserMessage.builder().response(userServices.findUser(username)).message("user details").status(true).build();
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiUserMessage response=ApiUserMessage.builder().message("user detail not found..").status(false).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
