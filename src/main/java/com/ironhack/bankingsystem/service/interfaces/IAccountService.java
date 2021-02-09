package com.ironhack.bankingsystem.service.interfaces;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAccountService {
    Money getAccountBalance(Long id);
    void setAccountBalance(Long id, BalanceDTO balance);
    Money getBalanceForAccount(Long id, UserDetails userDetails);
}
