package com.ironhack.bankingsystem.controller.impl;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;
import com.ironhack.bankingsystem.controller.dtos.MoneyDTO;
import com.ironhack.bankingsystem.controller.interfaces.IAccountController;
import com.ironhack.bankingsystem.model.Transaction;
import com.ironhack.bankingsystem.repository.AccountRepository;
import com.ironhack.bankingsystem.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/admin/account-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getAccountBalance(@PathVariable("id") Long id) {
        return accountService.getAccountBalance(id);
    }

    @PatchMapping("/admin/account-balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setAccountBalance(@PathVariable("id") Long id, @RequestBody @Valid BalanceDTO balance) {
        accountService.setAccountBalance(id, balance);
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceForAccount(@PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return accountService.getBalanceForAccount(id, userDetails);
    }

    @PatchMapping("/transfer-money")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@RequestBody @Valid Transaction transaction) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        accountService.transferMoney(transaction, userDetails);
    }

    @PatchMapping("/receive-money/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receiveMoney(@PathVariable("id") Long id, @RequestParam("secret-key") String secretKey,
                          @RequestBody @Valid MoneyDTO amount, @RequestHeader("Hashed-Key") String hashedKey) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        accountService.receiveMoney(id, secretKey, amount, hashedKey, userDetails);
    }

    @PatchMapping("/send-money/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoney(@PathVariable("id") Long id, @RequestParam("secret-key") String secretKey,
                             @RequestBody @Valid MoneyDTO amount, @RequestHeader("Hashed-Key") String hashedKey) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        accountService.sendMoney(id, secretKey, amount, hashedKey, userDetails);
    }
}
