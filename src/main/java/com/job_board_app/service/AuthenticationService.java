package com.job_board_app.service;

import com.job_board_app.model.ERole;
import com.job_board_app.model.User;
import com.job_board_app.payload.request.LoginUserDTO;
import com.job_board_app.payload.request.RegisterUserDTO;
import com.job_board_app.payload.response.AuthResponse;
import com.job_board_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterUserDTO registerUserDTO) {
        var user = User.builder()
                .firstname(registerUserDTO.getFirstname())
                .lastname(registerUserDTO.getLastname())
                .email(registerUserDTO.getEmail())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .role(ERole.USER)
                .createdAt(Instant.now())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefresh(new HashMap<>(), user);
        return AuthResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse authenticate(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword())
        );
        var user = userRepository.findByEmail(loginUserDTO.getEmail());
        String jwtToken = null, refreshToken = null, msg_ = null;
        if (user.isPresent()) {
            jwtToken = jwtService.generateToken(user.get());
            refreshToken = jwtService.generateRefresh(new HashMap<>(), user.get());
        }
        msg_ = "Invalid email or password";
        return AuthResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .msg_(msg_)
                .build();
    }


    public AuthResponse refreshToken(String refreshToken) {
        var user = userRepository.findByEmail(jwtService.getEmailFromToken(refreshToken));
        String jwtToken = null, newRefreshToken = null, msg_ = null;
        if (user.isPresent()) {
            jwtToken = jwtService.generateToken(user.get());
            newRefreshToken = jwtService.generateRefresh(new HashMap<>(), user.get());
        }
        msg_ = "Invalid refresh token";
        return AuthResponse.builder()
                .token(jwtToken)
                .refreshToken(newRefreshToken)
                .msg_(msg_)
                .build();
    }

    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}