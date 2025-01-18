package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatementRequest {
    private String accountNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer page;
    private Integer size;
}