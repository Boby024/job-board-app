package com.job_board_app.repository;

import com.job_board_app.model.ERole;
import com.job_board_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    Optional<User>  findByEmailOrUsername(String email, String username);
    User findByRole(ERole role);
}
