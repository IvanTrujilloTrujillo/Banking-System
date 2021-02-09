package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.classes.Money;
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
            if(creditCard.getCreditLimit() != null) {
                if(creditCard.getCreditLimit().getAmount().compareTo(BigDecimal.valueOf(100)) < 0 ||
                        creditCard.getCreditLimit().getAmount().compareTo(BigDecimal.valueOf(100000)) > 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The credit limit must be between 100 and 100,000");
                }
            } else {
                creditCard.setCreditLimit(new Money(BigDecimal.valueOf(100), creditCard.getBalance().getCurrency()));
            }

            if(creditCard.getInterestRate() != null) {
                if(creditCard.getInterestRate().compareTo(BigDecimal.valueOf(0.1)) < 0 ||
                        creditCard.getInterestRate().compareTo(BigDecimal.valueOf(0.2)) > 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The interest rate must be between 0.1 and 0.2");
                }
            } else {
                creditCard.setInterestRate(BigDecimal.valueOf(0.2));
            }
            return creditCardRepository.save(creditCard);
        }
    }
}
