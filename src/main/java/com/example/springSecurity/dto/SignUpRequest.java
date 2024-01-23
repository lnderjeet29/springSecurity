package com.example.springSecurity.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String Username;
    private String email;
    private String password;
}
