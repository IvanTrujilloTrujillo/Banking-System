package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("250");
    private static final BigDecimal MONTHLY_MAINTENANCE_FEE = new BigDecimal("12");

    @NotEmpty
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
    }

    public Checking(Money balance, AccountHolder primaryOwner, String secretKey) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return MONTHLY_MAINTENANCE_FEE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
