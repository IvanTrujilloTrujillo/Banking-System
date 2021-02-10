package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="credit_limit_currency")),
            @AttributeOverride(name="amount",column=@Column(name="credit_limit_amount")),
    })
    private Money creditLimit;
    @Digits(integer = 2, fraction = 2, message = "The interest rate must have two decimals")
    @DecimalMax(value = "0.2", message = "The interest rate must be smaller or equal than 0.2")
    @DecimalMin(value = "0.1", message = "The interest rate must be greater or equal than 0.1")
    private BigDecimal interestRate = new BigDecimal("0.2");
    private LocalDateTime lastInterestAddedDate;

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        try {
            setCreditLimit(creditLimit);
        } catch (IllegalArgumentException e) {
            setCreditLimit(new Money(BigDecimal.valueOf(100), creditLimit.getCurrency()));
        }
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        try {
            setCreditLimit(creditLimit);
        } catch (IllegalArgumentException e) {
            setCreditLimit(new Money(BigDecimal.valueOf(100), creditLimit.getCurrency()));
        }
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setCreditLimit(new Money(BigDecimal.valueOf(100), balance.getCurrency()));
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
        setCreditLimit(new Money(BigDecimal.valueOf(100), balance.getCurrency()));
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner);
        try {
            setCreditLimit(creditLimit);
        } catch (IllegalArgumentException e) {
            setCreditLimit(new Money(BigDecimal.valueOf(100), creditLimit.getCurrency()));
        }
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit) {
        super(balance, primaryOwner);
        try {
            setCreditLimit(creditLimit);
        } catch (IllegalArgumentException e) {
            setCreditLimit(new Money(BigDecimal.valueOf(100), creditLimit.getCurrency()));
        }
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setCreditLimit(new Money(BigDecimal.valueOf(100), balance.getCurrency()));
        setInterestRate(interestRate);
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public CreditCard(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
        setCreditLimit(new Money(BigDecimal.valueOf(100), balance.getCurrency()));
        setLastInterestAddedDate(LocalDateTime.now());
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit.getAmount().compareTo(BigDecimal.valueOf(100)) < 0 ||
                creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) > 0) {
            throw new IllegalArgumentException("The credit limit must be between 100 and 100,000");
        } else {
            this.creditLimit = creditLimit;
        }
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
