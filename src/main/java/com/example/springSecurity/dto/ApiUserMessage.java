package com.example.springSecurity.dto;

import com.example.springSecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiUserMessage {
    private String message;
    private boolean status;
    private User response;
}
