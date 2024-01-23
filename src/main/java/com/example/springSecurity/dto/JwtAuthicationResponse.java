package com.example.springSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthicationResponse {
    private String Token;
    private String refreshToken;
    private String email;
    boolean status;
}
