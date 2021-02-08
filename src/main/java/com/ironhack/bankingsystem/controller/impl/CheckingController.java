package com.ironhack.bankingsystem.controller.impl;

import com.ironhack.bankingsystem.controller.interfaces.ICheckingController;
import com.ironhack.bankingsystem.model.Checking;
import com.ironhack.bankingsystem.repository.CheckingRepository;
import com.ironhack.bankingsystem.service.impl.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CheckingController implements ICheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private CheckingService checkingService;

    @PostMapping("/admin/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createChecking(@RequestBody @Valid Checking checking) {
        return checkingService.createChecking(checking);
    }
}
