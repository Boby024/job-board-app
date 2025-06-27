package com.job_board_app.service;

import com.job_board_app.model.User;
import com.job_board_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;

    public User getUserByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username).orElse(null);
    }

    public User getUserById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
