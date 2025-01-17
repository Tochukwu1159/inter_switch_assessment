package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccountRequestDTO {
    private BigDecimal initialDeposit;
}
