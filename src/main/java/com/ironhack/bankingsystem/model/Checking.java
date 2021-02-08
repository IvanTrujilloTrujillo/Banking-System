package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking  extends Account{
    private String secretKey;
    private Integer minimumBalance;
    private Integer monthlyMaintenanceFee;
    private Status status;

    public Checking() {
    }

    public Checking(Money balance, User primaryOwner, User secondaryOwner, String secretKey, Integer minimumBalance, Integer monthlyMaintenanceFee, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setStatus(status);
    }

    public Checking(Money balance, User primaryOwner, String secretKey, Integer minimumBalance, Integer monthlyMaintenanceFee, Status status) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setStatus(status);
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

    public Integer getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Integer monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
