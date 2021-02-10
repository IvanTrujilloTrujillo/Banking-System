package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT MAX(transactionDate) FROM Transaction WHERE senderAccount = :senderAccount")
    Optional<LocalDateTime> findLastTransactionBySenderAccount(@Param("senderAccount") Account senderAccount);
}
