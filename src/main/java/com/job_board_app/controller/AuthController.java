package com.job_board_app.controller;

import com.job_board_app.payload.request.LoginUserDTO;
import com.job_board_app.payload.request.RegisterUserDTO;
import com.job_board_app.payload.response.AuthResponse;
import com.job_board_app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserDTO registerUserDTO) {
        try {
            AuthResponse res = authenticationService.register(registerUserDTO);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserDTO loginUserDTO) {
        try {
            AuthResponse res = authenticationService.authenticate(loginUserDTO);
            if (res.getMsg_() != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestParam("token") String refreshToken) {
        try {
            AuthResponse res = authenticationService.refreshToken(refreshToken);
            if (res.getMsg_() != null) {
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
        try {
            Boolean res = authenticationService.validateToken(token);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
