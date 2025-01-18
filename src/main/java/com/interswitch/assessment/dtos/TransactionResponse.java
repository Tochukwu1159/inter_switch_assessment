package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Double amount;

    private String type;

    private String description;

    private LocalDateTime transactionDate;

    // Constructor, Getters, and Setters
}