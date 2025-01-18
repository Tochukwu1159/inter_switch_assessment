package com.interswitch.assessment.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than zero")
    private Double amount;

    // Getters and Setters
}

