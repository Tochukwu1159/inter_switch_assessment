package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountStatementResponseDTO {
    private Long accountId;
    private Double balance;
    private String transactionDate;
    private String description;
}
