package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account{
    @NotEmpty
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="minimum_balance_currency")),
            @AttributeOverride(name="amount",column=@Column(name="minimum_balance_amount")),
    })
    private Money minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Digits(integer = 2, fraction = 4, message = "The interest rate must have four decimals")
    @DecimalMax(value = "0.5", message = "The interest rate must be smaller or equal than 0.5")
    @DecimalMin(value = "0.0", message = "The interest rate must be greater or equal than 0.0")
    private BigDecimal interestRate = new BigDecimal("0.0025");
    private LocalDateTime lastInterestAddedDate;

    public Saving() {
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        try {
            setMinimumBalance(minimumBalance);
        } catch (IllegalArgumentException e) {
            setMinimumBalance(new Money(BigDecimal.valueOf(1000), minimumBalance.getCurrency()));
        }
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Money minimumBalance) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        try {
            setMinimumBalance(minimumBalance);
        } catch (IllegalArgumentException e) {
            setMinimumBalance(new Money(BigDecimal.valueOf(1000), minimumBalance.getCurrency()));
        }
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(new Money(BigDecimal.valueOf(1000), balance.getCurrency()));
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(new Money(BigDecimal.valueOf(1000), balance.getCurrency()));
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        try {
            setMinimumBalance(minimumBalance);
        } catch (IllegalArgumentException e) {
            setMinimumBalance(new Money(BigDecimal.valueOf(1000), minimumBalance.getCurrency()));
        }
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey, Money minimumBalance) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        try {
            setMinimumBalance(minimumBalance);
        } catch (IllegalArgumentException e) {
            setMinimumBalance(new Money(BigDecimal.valueOf(1000), minimumBalance.getCurrency()));
        }
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(new Money(BigDecimal.valueOf(1000), balance.getCurrency()));
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Saving(Money balance, AccountHolder primaryOwner, String secretKey) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setMinimumBalance(new Money(BigDecimal.valueOf(1000), balance.getCurrency()));
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if(minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100)) < 0 ||
                minimumBalance.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            throw new IllegalArgumentException("The minimum balance must be between 100 and 1,000");
        } else {
            this.minimumBalance = minimumBalance;
        }
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

    public LocalDateTime getLastInterestAddedDate() {
        return lastInterestAddedDate;
    }

    public void setLastInterestAddedDate(LocalDateTime lastInterestAddedDate) {
        this.lastInterestAddedDate = lastInterestAddedDate;
    }
}
