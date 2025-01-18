package com.interswitch.assessment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountRequest {

    @NotNull
    private String accountNumber;

    @NotNull
    @Positive
    private Double amount;

    private String description;

    public CreditAccountRequest(Double amount, String description) {
        this.amount = amount;
        this.description = description;
    }}