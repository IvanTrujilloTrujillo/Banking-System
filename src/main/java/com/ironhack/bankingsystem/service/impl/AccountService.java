package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;
import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.repository.AccountRepository;
import com.ironhack.bankingsystem.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Currency;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

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
}
