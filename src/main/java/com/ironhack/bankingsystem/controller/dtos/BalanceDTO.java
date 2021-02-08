package com.ironhack.bankingsystem.controller.dtos;

import com.ironhack.bankingsystem.classes.Money;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class BalanceDTO {
    private String currency = "USD";
    @Digits(integer = 19, fraction = 2, message = "The amount must have two decimals")
    private BigDecimal amount;

    public BalanceDTO() {
    }

    public BalanceDTO(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public BalanceDTO(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
