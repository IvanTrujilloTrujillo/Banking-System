package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account{
    private String secretKey;
    private Integer minimumBalance;
    private Status status;
    private BigDecimal interestRate;

    public Saving() {
    }

    public Saving(Money balance, User primaryOwner, User secondaryOwner, String secretKey, Integer minimumBalance, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setStatus(status);
        setInterestRate(interestRate);
    }

    public Saving(Money balance, User primaryOwner, String secretKey, Integer minimumBalance, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setStatus(status);
        setInterestRate(interestRate);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Integer minimumBalance) {
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
