package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;
import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.Transaction;
import com.ironhack.bankingsystem.repository.AccountRepository;
import com.ironhack.bankingsystem.repository.CheckingRepository;
import com.ironhack.bankingsystem.repository.SavingRepository;
import com.ironhack.bankingsystem.repository.TransactionRepository;
import com.ironhack.bankingsystem.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Money getAccountBalance(Long id) {
        if(accountRepository.existsById(id)) {
            return accountRepository.findById(id).get().getBalance();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id doesn't exist");
        }
    }

    public void setAccountBalance(Long id, BalanceDTO balance) {
        if(accountRepository.existsById(id)) {
            Account account = accountRepository.findById(id).get();
            try {
                Money newBalance = new Money(balance.getAmount(), Currency.getInstance(balance.getCurrency()));
                account.setBalance(newBalance);
                accountRepository.save(account);
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It isn't a supported ISO 4217 code");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id doesn't exist");
        }
    }

    public Money getBalanceForAccount(Long id, UserDetails userDetails) {
        if(accountRepository.existsById(id)) {
            if(accountRepository.findById(id).get().getPrimaryOwner().getUsername().equals(userDetails.getUsername()) ||
                    (accountRepository.findById(id).get().getSecondaryOwner() != null &&
                     accountRepository.findById(id).get().getSecondaryOwner().getUsername().equals(userDetails.getUsername()))) {
                if(checkingRepository.existsById(id)) {
                    if(checkingRepository.findById(id).get().getBalance().getAmount().compareTo(
                            checkingRepository.findById(id).get().getMinimumBalance().getAmount()) < 0) {

                        checkingRepository.findById(id).get().getBalance().decreaseAmount(
                                checkingRepository.findById(id).get().getPenaltyFee().getAmount());

                    }
                } else if (savingRepository.existsById(id)) {
                    if(savingRepository.findById(id).get().getBalance().getAmount().compareTo(
                            savingRepository.findById(id).get().getMinimumBalance().getAmount()) < 0) {

                        savingRepository.findById(id).get().getBalance().decreaseAmount(
                                savingRepository.findById(id).get().getPenaltyFee().getAmount());

                    }
                }

                return accountRepository.findById(id).get().getBalance();
            }
            else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don´t have access to this account");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id doesn't exist");
        }
    }

    public void transferMoney(Transaction transaction, UserDetails userDetails) {
        Account senderAccount = transaction.getSenderAccount();
        Long receiverAccountId = transaction.getReceiverAccountId();

        if(senderAccount.getPrimaryOwner().getUsername().equals(userDetails.getUsername()) ||
                (senderAccount.getSecondaryOwner() != null &&
                 senderAccount.getSecondaryOwner().getUsername().equals(userDetails.getUsername()))) {
            if(transaction.getAmount().getAmount().compareTo(
                    senderAccount.getBalance().getAmount()) < 0) {
                if(accountRepository.existsById(receiverAccountId)) {
                    if (accountRepository.findById(receiverAccountId).get().getPrimaryOwner().getName().equals(
                            transaction.getReceiverAccountHolderName()) ||
                            (accountRepository.findById(receiverAccountId).get().getSecondaryOwner() != null &&
                             accountRepository.findById(receiverAccountId).get().getSecondaryOwner().getName().equals(
                             transaction.getReceiverAccountHolderName()))) {
                        senderAccount.getBalance().decreaseAmount(transaction.getAmount());   //TODO: Comprobar que funciona
                        accountRepository.findById(receiverAccountId).get().getBalance().increaseAmount(transaction.getAmount());
                        accountRepository.save(senderAccount);
                        transaction.setTransactionDate(LocalDateTime.now());
                        transactionRepository.save(transaction);
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The receiver name doesn't match " +
                                "with the owners of the Account Id");
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account Id of the receiver " +
                            "doesn't exist");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You don´t have enough balance");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don´t have access to the sender account");
        }
    }
}
