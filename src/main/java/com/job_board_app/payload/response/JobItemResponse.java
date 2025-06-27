package com.job_board_app.payload.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobItemResponse {
    private String id;
    private String msg_;
}
