package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccountNumberAndTransactionDateBetween(
            String accountNumber,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
