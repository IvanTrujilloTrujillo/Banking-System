package com.ironhack.bankingsystem.controller.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class MoneyDTO {
    @Size(max = 3)
    private String currency = "USD";
    @Digits(integer = 19, fraction = 2, message = "The amount must have two decimals")
    @DecimalMin("0.00")
    private BigDecimal amount;

    public MoneyDTO() {
    }

    public MoneyDTO(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public MoneyDTO(BigDecimal amount) {
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
