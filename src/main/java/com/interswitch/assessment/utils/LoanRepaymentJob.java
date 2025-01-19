package com.interswitch.assessment.utils;

import com.interswitch.assessment.dtos.CreditAccountRequest;
import com.interswitch.assessment.model.Loan;
import com.interswitch.assessment.model.LoanRepaymentSchedule;
import com.interswitch.assessment.repository.LoanRepaymentScheduleRepository;
import com.interswitch.assessment.repository.LoanRepository;
import com.interswitch.assessment.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanRepaymentJob {

    private final LoanRepaymentScheduleRepository repaymentScheduleRepository;

    private final AccountService accountService;

    private final LoanRepository loanRepository;

//    @Scheduled(cron = "${loan.repayment.cron}") // Externalize cron expression
@Scheduled(cron = "0 0/2 * * * *") // for test
    @Transactional
    public void processLoanRepayments() {
        List<LoanRepaymentSchedule> dueRepayments = repaymentScheduleRepository
                .findByRepaymentDateBeforeAndIsPaidFalse(LocalDateTime.now());
    System.out.println(dueRepayments);
        for (LoanRepaymentSchedule schedule : dueRepayments) {
            Loan loan = schedule.getLoan();

            try {
                // Debit account for the repayment amount
                CreditAccountRequest debitRequest = new CreditAccountRequest(loan.getAccountNumber(),schedule.getAmount(), "Loan repayment");

                accountService.debit(debitRequest);

                // Mark the repayment schedule as paid
                schedule.setIsPaid(true);
                repaymentScheduleRepository.save(schedule);

                // Update loan outstanding balance
                loan.setOutstandingBalance(loan.getOutstandingBalance() - schedule.getAmount());
                if (loan.getOutstandingBalance() <= 0) {
                    loan.setStatus(LoanStatus.COMPLETED);
                }
                loanRepository.save(loan);
            } catch (IllegalArgumentException e) {
                System.err.println("Failed to process repayment for account: "
                        + loan.getAccountNumber() + ", reason: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error while processing repayment for loan with account: "
                        + loan.getAccountNumber() + ", reason: " + e.getMessage());
            }
        }
    }
}