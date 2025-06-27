package com.job_board_app.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.job_board_app.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class RegisterUserDTO {
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @Size(max = 255)
    @Email(message = "Email Invalid!")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Size(max = 255)
    @NotNull(message = "Firstname cannot be null")
    private String firstname;

    @Size(max = 255)
    @NotNull(message = "Lastname cannot be null")
    private String lastname;

    // format: yyyy-MM-dd
    @NotNull(message = "BirthDate cannot be null")
    private LocalDate birthDate;

    private String description;


    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setDescription(description);
        user.setBirthDate(birthDate);
        user.setCreatedAt(Instant.now());
        return user;
    }
}
