package com.example.ordermsa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;

@Entity(name = "users", indexes={@Index(name = "idx_users_status")})
public class User {

    @Id
    private Integer id;
}
