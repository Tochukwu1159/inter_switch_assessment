package com.interswitch.assessment.repository;

import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.model.LoanRepaymentSchedule;
import com.interswitch.assessment.utils.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRepaymentScheduleRepository extends JpaRepository<LoanRepaymentSchedule, Long> {

    List<LoanRepaymentSchedule> findByRepaymentDateBeforeAndIsPaidFalse(LocalDateTime now);
}

