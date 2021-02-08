package com.ironhack.bankingsystem.service.impl;

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
            return savingRepository.save(saving);
        }
    }
}
