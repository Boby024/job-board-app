package com.job_board_app.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginUserDTO {
    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email Invalid!")
    private String email;

//    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;
}
