package com.example.ordermsa.service;

import com.example.ordermsa.api.dto.UserSignUpRequest;
import com.example.ordermsa.api.dto.UserSignUpResponse;
import com.example.ordermsa.domain.User;
import com.example.ordermsa.domain.UserStatus;
import com.example.ordermsa.domain.repository.UserRepository;
import com.example.ordermsa.role.Role;
import com.example.ordermsa.role.RoleRepository;
import com.example.ordermsa.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) {

        if(userRepository.existsByEmail(userSignUpRequest.getEmail())){
            throw new IllegalArgumentException("이미 사용중인 이메일 입니다.");
        }

        if (userSignUpRequest.getPhone() != null && userRepository.existsByPhone(userSignUpRequest.getPhone())){
            throw new IllegalArgumentException("이미 사용중인 번호 입니다.");
        }

        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(()-> new IllegalArgumentException("기본 권한(ROLE_USER)이 설정되어있지 않습니다."));

        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .name(userSignUpRequest.getName())
                .phone(userSignUpRequest.getPhone())
                .status(UserStatus.ACTIVE)
                .createdAt(now)
                .updatedAt(now)
                .roles(Set.of(userRole))
                .build();

        User saved = userRepository.save(user);

        //응답 DTO 변환
        return UserSignUpResponse.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .name(saved.getName())
                .phone(saved.getPhone())
                .createdAt(saved.getCreatedAt())
                .build();
    }


}
