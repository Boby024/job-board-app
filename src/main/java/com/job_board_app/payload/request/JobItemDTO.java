package com.job_board_app.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class JobItemDTO {
    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Location cannot be null")
    private String location;

    @NotNull(message = "Company cannot be null")
    private String company;

    @NotNull(message = "Type cannot be null, Full-Time, Part-Time, Contract")
    private String type; // e.g., Full-Time, Part-Time, Contract

    private Double salary;

    private Double minSalary;

    private Double maxSalary;
}
