package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private AccountHolder senderAccount;
    @ManyToOne
    @NotNull
    private AccountHolder receiverAccount;
    @Embedded
    @NotNull
    private Money amount;
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(AccountHolder senderAccount, AccountHolder receiverAccount, Money amount) {
        setSenderAccount(senderAccount);
        setReceiverAccount(receiverAccount);
        setAmount(amount);
        this.transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountHolder getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(AccountHolder senderAccount) {
        this.senderAccount = senderAccount;
    }

    public AccountHolder getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(AccountHolder receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}
