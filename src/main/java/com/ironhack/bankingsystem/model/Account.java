package com.ironhack.bankingsystem.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public abstract class Account {

    private static final Integer PENALTY_FEE = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    @OneToOne
    private User primaryOwner;
    @OneToOne
    private User secondaryOwner;

    public Account() {
    }

    public Account(BigDecimal balance, User primaryOwner, User secondaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
    }

    public Account(BigDecimal balance, User primaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(User primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public User getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(User secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }
}