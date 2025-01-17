package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByIsPaidOff(boolean isPaidOff);
}
