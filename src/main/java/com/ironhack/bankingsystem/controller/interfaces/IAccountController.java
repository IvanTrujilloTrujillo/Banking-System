package com.ironhack.bankingsystem.controller.interfaces;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;

public interface IAccountController {
    Money getAccountBalance(Long id);
    void setAccountBalance(Long id, BalanceDTO balance);
    Money getBalanceForAccount(Long id);
}
