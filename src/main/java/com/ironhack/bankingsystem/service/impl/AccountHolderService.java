package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.model.AccountHolder;
import com.ironhack.bankingsystem.repository.AccountHolderRepository;
import com.ironhack.bankingsystem.repository.UserRepository;
import com.ironhack.bankingsystem.service.interfaces.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderService implements IAccountHolderService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private UserRepository userRepository;

    public AccountHolder createAccountHolder(AccountHolder accountHolder) {
        if(userRepository.findByUsername(accountHolder.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username already exists");
        } else {
            return accountHolderRepository.save(accountHolder);
        }
    }
}
