package com.ironhack.bankingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bankingsystem.classes.Money;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="balance_currency")),
            @AttributeOverride(name="amount",column=@Column(name="balance_amount")),
    })
    @NotNull
    private Money balance;
    @OneToOne
    @NotNull
    private AccountHolder primaryOwner;
    @OneToOne
    private AccountHolder secondaryOwner;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="penalty_fee_currency")),
            @AttributeOverride(name="amount",column=@Column(name="penalty_fee_amount")),
    })
    private Money penaltyFee;
    private LocalDateTime creationDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="currency",column=@Column(name="max_limit_transactions_currency")),
            @AttributeOverride(name="amount",column=@Column(name="max_limit_transactions_amount")),
    })
    private Money maxLimitTransactions;

    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setCreationDate();
        setPenaltyFee();
        setMaxLimitTransactions(new Money(BigDecimal.valueOf(0), balance.getCurrency()));
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setCreationDate();
        setPenaltyFee();
        setMaxLimitTransactions(new Money(BigDecimal.valueOf(0), balance.getCurrency()));
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

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee() {
        this.penaltyFee = new Money(BigDecimal.valueOf(40), this.balance.getCurrency());
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public Money getMaxLimitTransactions() {
        return maxLimitTransactions;
    }

    public void setMaxLimitTransactions(Money maxLimitTransactions) {
        this.maxLimitTransactions = maxLimitTransactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
