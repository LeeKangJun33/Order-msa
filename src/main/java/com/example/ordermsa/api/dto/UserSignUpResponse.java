package com.example.ordermsa.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserSignUpResponse {

    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
