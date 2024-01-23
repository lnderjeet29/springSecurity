package com.example.springSecurity.dto;

import lombok.Data;

@Data
public class JwtAuthicationResponse {
    private String Token;
    private String refreshToken;
    private String email;
    private boolean status;
}
