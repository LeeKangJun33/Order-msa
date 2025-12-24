package com.example.ordermsa.api;

import com.example.ordermsa.api.dto.UserSignUpRequest;
import com.example.ordermsa.api.dto.UserSignUpResponse;
import com.example.ordermsa.config.JwtTokenProvider;
import com.example.ordermsa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입 API
     * Post /api/users/signup
     */

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponse> signUp(@Valid @RequestBody UserSignUpRequest request) {
        UserSignUpResponse response = userService.signUp(request);
        return ResponseEntity.ok(response);
    }




}
