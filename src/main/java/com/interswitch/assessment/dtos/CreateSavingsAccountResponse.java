package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSavingsAccountResponse {
    private String accountNumber;
    private Double balance;
}