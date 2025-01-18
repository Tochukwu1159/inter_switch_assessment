package com.interswitch.assessment.model;

import com.interswitch.assessment.utils.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String accountNumber;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanStatus status; // REQUESTED, APPROVED, REJECTED, COMPLETED

    private LocalDateTime requestDate;

    private LocalDateTime approvalDate;

    private String rejectReason;

    private LocalDateTime repaymentStartDate;

    private Double outstandingBalance;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<LoanRepaymentSchedule> repaymentSchedules;

    // Getters and Setters
}
