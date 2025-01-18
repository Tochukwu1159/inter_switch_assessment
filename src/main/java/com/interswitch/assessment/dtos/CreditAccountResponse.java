package com.interswitch.assessment.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountResponse {
    private String accountNumber;
    private Double newBalance;
}
