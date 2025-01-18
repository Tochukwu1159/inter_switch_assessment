package com.interswitch.assessment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponse {
    private List<TransactionResponse> transactions;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    // Constructor, Getters, and Setters
}
