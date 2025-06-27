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
    private String keyword; // title or description
    private String location;
    private String type;
    private String company;
    private Double minSalary;
    private Double maxSalary;
    Integer page;
    Integer size;
    String sortBy;
    String sortDir;
}
