package com.interswitch.assessment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "savings_accounts") // Specifies the table name
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2) // Allows for precise financial calculations
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false, unique = true) // Ensures each customer has only one account
    private Customer customer;

    public SavingsAccount(Customer customer, BigDecimal balance) {
        this.customer = customer;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
    }

    // Utility methods
    public void credit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to credit must be greater than zero.");
        }
        this.balance = this.balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to debit must be greater than zero.");
        }
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalStateException("Insufficient balance for the transaction.");
        }
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", customer=" + (customer != null ? customer.getId() : "null") +
                '}';
    }
}
