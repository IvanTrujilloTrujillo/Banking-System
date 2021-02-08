package com.ironhack.bankingsystem.service.interfaces;

import com.ironhack.bankingsystem.classes.Money;

public interface IAccountService {
    Money getAccountBalance(Long id);
}
