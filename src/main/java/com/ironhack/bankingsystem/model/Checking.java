package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{

    @NotEmpty
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="minimum_balance_currency")),
            @AttributeOverride(name="amount",column=@Column(name="minimum_balance_amount")),
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="monthly_maintenance_fee_currency")),
            @AttributeOverride(name="amount",column=@Column(name="monthly_maintenance_fee_amount")),
    })
    private Money monthlyMaintenanceFee;

    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(balance.getCurrency());
        setMonthlyMaintenanceFee(balance.getCurrency());
    }

    public Checking(Money balance, AccountHolder primaryOwner, String secretKey) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(balance.getCurrency());
        setMonthlyMaintenanceFee(balance.getCurrency());
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Currency currency) {
        this.minimumBalance = new Money(BigDecimal.valueOf(250), currency);
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Currency currency) {
        this.monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12), currency);
    }
}
