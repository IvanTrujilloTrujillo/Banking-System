package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.model.Saving;
import com.ironhack.bankingsystem.repository.SavingRepository;
import com.ironhack.bankingsystem.service.interfaces.ISavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class SavingService implements ISavingService {

    @Autowired
    private SavingRepository savingRepository;

    public Saving createSaving(Saving saving) {
        if(saving.getBalance().getAmount().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The balance must be greater or equals than 0");
        } else {
            if(saving.getMinimumBalance() != null) {
                if(saving.getMinimumBalance().getAmount().compareTo(BigDecimal.valueOf(100)) < 0 ||
                        saving.getMinimumBalance().getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The minimum balance must be between 100 and 1000");
                }
            } else {
                saving.setMinimumBalance(new Money(BigDecimal.valueOf(1000), saving.getBalance().getCurrency()));
            }

            if(saving.getInterestRate() != null) {
                if(saving.getInterestRate().compareTo(BigDecimal.valueOf(0)) < 0 ||
                        saving.getInterestRate().compareTo(BigDecimal.valueOf(0.5)) > 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The interest rate must be between 0.0 and 0.5");
                }
            } else {
                saving.setInterestRate(BigDecimal.valueOf(0.0025));
            }
            saving.setPenaltyFee();
            saving.setCreationDate();
            saving.setMaxLimitTransactions(new Money(BigDecimal.valueOf(0), saving.getBalance().getCurrency()));
            return savingRepository.save(saving);
        }
    }
}
