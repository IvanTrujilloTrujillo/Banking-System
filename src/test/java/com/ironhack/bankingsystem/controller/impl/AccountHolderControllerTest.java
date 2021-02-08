package com.ironhack.bankingsystem.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingsystem.classes.Address;
import com.ironhack.bankingsystem.model.AccountHolder;
import com.ironhack.bankingsystem.repository.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private AccountHolder accountHolder;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        accountHolder = new AccountHolder("Manuel Gómez", "manuelg", "1234",
                LocalDateTime.of(1995, 2, 5, 0, 0),
                new Address("Calle Benito Pérez", 10, 2, "A", "30254", "Madrid", "Spain")
        );

        accountHolderRepository.save(accountHolder);
    }

    @AfterEach
    public void tearDown() {
        accountHolderRepository.deleteAll();
    }

    @Test
    public void createAccountHolder() {
    }
}