package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account{
    @NotEmpty
    private String secretKey;
    @Digits(integer = 8, fraction = 0, message = "The minimum balance mustn't have decimals")
    @DecimalMax(value = "1000", message = "The minimum balance must be smaller or equal than 1000")
    @DecimalMin(value = "100", message = "The minimum balance must be greater or equal than 100")
    private BigDecimal minimumBalance = new BigDecimal("1000");
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Digits(integer = 2, fraction = 4, message = "The interest rate must have four decimals")
    @DecimalMax(value = "0.5", message = "The interest rate must be smaller or equal than 0.5")
    @DecimalMin(value = "0.0", message = "The interest rate must be greater or equal than 0.0")
    private BigDecimal interestRate = new BigDecimal("0.0025");

    public Saving() {
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey) {
        super(balance, primaryOwner);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
