package com.job_board_app.payload.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String id;
    private String email;
    private String token;
    private String refreshToken;
    private Long expiresAt;
    private String msg_;
}
