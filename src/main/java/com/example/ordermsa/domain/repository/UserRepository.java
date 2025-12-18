package com.example.ordermsa.domain.repository;

import com.example.ordermsa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
