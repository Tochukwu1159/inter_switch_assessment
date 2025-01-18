package com.interswitch.assessment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementRequest {

    @NotNull(message = "Account number is required")
    private String accountNumber;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer page = 0;

    private Integer size = 10;

    // Getters and Setters
}





