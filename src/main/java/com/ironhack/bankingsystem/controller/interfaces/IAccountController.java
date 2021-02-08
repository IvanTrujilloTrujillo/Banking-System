package com.ironhack.bankingsystem.controller.interfaces;

import com.ironhack.bankingsystem.classes.Money;

public interface IAccountController {
    Money getAccountBalance(Long id);
}
