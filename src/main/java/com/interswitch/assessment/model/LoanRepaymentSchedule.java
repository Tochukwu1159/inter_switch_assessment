package com.interswitch.assessment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LoanRepaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    @ToString.Exclude
    private Loan loan;

    @NotNull
    private LocalDateTime repaymentDate;

    @NotNull
    private Double amount;

    private Boolean isPaid = false;
}

