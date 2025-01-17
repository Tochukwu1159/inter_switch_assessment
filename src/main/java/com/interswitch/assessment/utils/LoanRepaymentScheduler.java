package com.interswitch.assessment.utils;

import com.interswitch.assessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoanRepaymentScheduler {

    @Autowired
    private LoanService loanService;

    @Scheduled(cron = "0 0 3 * * ?")
    public void automaticLoanRepayment() {
        loanService.repayLoans();
    }
}
