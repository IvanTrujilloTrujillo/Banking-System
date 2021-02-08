package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    private static final Integer PENALTY_FEE = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @NotNull
    private Money balance;
    @OneToOne
    @NotNull
    private AccountHolder primaryOwner;
    @OneToOne
    private AccountHolder secondaryOwner;
    private LocalDateTime creationDate;
    private BigDecimal maxLimitTransactions = new BigDecimal("100");

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        this.creationDate = LocalDateTime.now();
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        this.creationDate = LocalDateTime.now();
    }

    public Integer getPenaltyFee() {
        return PENALTY_FEE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public User getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public User getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
