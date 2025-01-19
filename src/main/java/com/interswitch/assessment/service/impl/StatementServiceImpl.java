package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.StatementRequest;
import com.interswitch.assessment.dtos.StatementResponse;
import com.interswitch.assessment.dtos.TransactionResponse;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.model.Transaction;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.repository.TransactionRepository;
import com.interswitch.assessment.service.StatementService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {


    private final  TransactionRepository transactionRepository;
    private final SavingsAccountRepository savingsAccountRepository;

    @Override
    public StatementResponse getAccountStatement(StatementRequest request) {

        SavingsAccount savingsAccount = savingsAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        LocalDateTime startDate = request.getStartDate() != null
                ? request.getStartDate().atStartOfDay()
                : LocalDateTime.MIN;

        LocalDateTime endDate = request.getEndDate() != null
                ? request.getEndDate().atTime(23, 59, 59)
                : LocalDateTime.now();

        // Pagination
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "transactionDate"));

        // Fetch transactions
        Page<Transaction> transactionPage = transactionRepository.findByAccountNumberAndTransactionDateBetween(
                request.getAccountNumber(),
                startDate,
                endDate,
                pageable
        );

        // Convert entities to response DTO
        List<TransactionResponse> transactions = transactionPage.getContent().stream()
                .map(tx -> new TransactionResponse(
                        tx.getAmount(),
                        tx.getType().toString(),
                        tx.getDescription(),
                        tx.getTransactionDate()
                ))
                .collect(Collectors.toList());

        return new StatementResponse(
                transactions,
                transactionPage.getTotalPages(),
                transactionPage.getTotalElements(),
                transactionPage.getNumber()
        );
    }
    }

