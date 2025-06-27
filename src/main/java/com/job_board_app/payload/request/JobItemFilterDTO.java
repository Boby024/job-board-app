package com.job_board_app.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JobItemFilterDTO {
    private String keyword;
    private String location;
    private String type;
    private String company;
    Integer page = 0;
    Integer size = 10;
    String sortBy;
    String sortDir;
}
