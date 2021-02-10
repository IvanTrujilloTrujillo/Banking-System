package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT MAX(transaction_date) FROM transaction WHERE sender_account_id = ?")
    LocalDateTime findLastTransactionBySenderAccount(Account senderAccount);
}
