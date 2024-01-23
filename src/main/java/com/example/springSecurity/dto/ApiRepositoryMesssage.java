package com.example.springSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRepositoryMesssage {
    private String message;
    private boolean status;
    private HttpStatus httpStatus;
    private JwtAuthicationResponse response;
}
