package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{
    @Digits(integer = 8, fraction = 0, message = "The credit limit mustn't have decimals")
    @DecimalMax(value = "100000", message = "The credit limit must be smaller or equal than 100,000")
    @DecimalMin(value = "100", message = "The credit limit must be greater or equal than 100")
    private BigDecimal creditLimit = new BigDecimal("100");
    @Digits(integer = 2, fraction = 1, message = "The interest rate must have only one decimal")
    @DecimalMax(value = "0.2", message = "The interest rate must be smaller or equal than 0.2")
    @DecimalMin(value = "0.1", message = "The interest rate must be greater or equal than 0.1")
    private BigDecimal interestRate = new BigDecimal("0.2");

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
