package com.interswitch.assessment.service.impl;

import com.interswitch.assessment.dtos.LoanApprovalRequest;
import com.interswitch.assessment.dtos.LoanRequest;
import com.interswitch.assessment.dtos.LoanResponse;
import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.model.LoanRepaymentSchedule;
import com.interswitch.assessment.model.SavingsAccount;
import com.interswitch.assessment.repository.LoanRepaymentScheduleRepository;
import com.interswitch.assessment.repository.LoanRepository;
import com.interswitch.assessment.repository.SavingsAccountRepository;
import com.interswitch.assessment.service.AccountService;
import com.interswitch.assessment.service.LoanService;
import com.interswitch.assessment.utils.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {


    private final LoanRepository loanRepository;

    private final LoanRepaymentScheduleRepository repaymentScheduleRepository;

    private final SavingsAccountRepository savingsAccountRepository;

    public LoanResponse requestLoan(LoanRequest request) {

        SavingsAccount savingsAccount = savingsAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Optional<Loan> activeLoan = loanRepository.findFirstByAccountNumberAndStatus(request.getAccountNumber(), LoanStatus.APPROVED);
        if (activeLoan.isPresent()) {
            throw new IllegalArgumentException("You must repay your previous loan before requesting a new one.");
        }

        // Create Loan
        Loan loan = new Loan();
        loan.setAccountNumber(request.getAccountNumber());
        loan.setAmount(request.getAmount());
        loan.setOutstandingBalance(request.getAmount());
        loan.setStatus(LoanStatus.REQUESTED);
        loan.setRequestDate(LocalDateTime.now());
        loanRepository.save(loan);

        return new LoanResponse(
                loan.getId(),
                loan.getAccountNumber(),
                loan.getAmount(),
                loan.getStatus(),
                loan.getOutstandingBalance(),
                loan.getRequestDate(),
                null
        );
    }

    public LoanResponse approveOrRejectLoan(LoanApprovalRequest request) {
        Loan loan = loanRepository.findById(request.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        Optional<Loan> existingLoan = loanRepository.findFirstByAccountNumberAndStatus(loan.getAccountNumber(), LoanStatus.APPROVED);
        if (existingLoan.isPresent()) {
            throw new IllegalArgumentException("The customer already has an approved loan.");
        }

        if (loan.getStatus() != LoanStatus.REQUESTED) {
            throw new IllegalArgumentException("Only requested loans can be approved or rejected.");
        }

        // we can have additional checks like calculateDebtToIncomeRatio, maximum loan amount due to customer tier status/level, etc

        if (request.getApprove()) {
            loan.setStatus(LoanStatus.APPROVED);
            loan.setApprovalDate(LocalDateTime.now());
            loan.setRepaymentStartDate(LocalDateTime.now().plusMonths(1));
            createRepaymentSchedule(loan);
        } else {
            loan.setStatus(LoanStatus.REJECTED);
            loan.setRejectReason(request.getRejectReason());
        }

        loanRepository.save(loan);

        return new LoanResponse(
                loan.getId(),
                loan.getAccountNumber(),
                loan.getAmount(),
                loan.getStatus(),
                loan.getOutstandingBalance(),
                loan.getRequestDate(),
                loan.getApprovalDate()
        );
    }

    private void createRepaymentSchedule(Loan loan) {
        double monthlyRepayment = loan.getAmount() / 10;

        for (int i = 1; i <= 10; i++) {
            LoanRepaymentSchedule schedule = new LoanRepaymentSchedule();
            schedule.setLoan(loan);
            schedule.setRepaymentDate(loan.getRepaymentStartDate().plusMonths(i));
            schedule.setAmount(monthlyRepayment);
            repaymentScheduleRepository.save(schedule);
        }
    }
}
