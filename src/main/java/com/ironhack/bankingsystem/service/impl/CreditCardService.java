package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.model.CreditCard;
import com.ironhack.bankingsystem.repository.CreditCardRepository;
import com.ironhack.bankingsystem.service.interfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard createCreditCard(CreditCard creditCard) {
        if(creditCard.getBalance().getAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The balance must be greater or equals than 0");
        } else {
            return creditCardRepository.save(creditCard);
        }
    }
}
