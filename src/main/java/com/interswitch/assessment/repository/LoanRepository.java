package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.utils.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findFirstByAccountNumberAndStatus(String accountNumber, LoanStatus status);
}
