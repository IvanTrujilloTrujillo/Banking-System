package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {
    private String secretKey;
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, User primaryOwner, User secondaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setStatus(status);
    }

    public StudentChecking(BigDecimal balance, User primaryOwner, String secretKey, Status status) {
        super(balance, primaryOwner);
        setSecretKey(secretKey);
        setStatus(status);
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
}
