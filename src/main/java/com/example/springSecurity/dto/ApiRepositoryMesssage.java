package com.example.springSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRepositoryMesssage {
    private String message;
    private boolean status;
    private JwtAuthicationResponse response;
}
