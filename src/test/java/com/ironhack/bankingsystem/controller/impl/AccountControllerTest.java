package com.ironhack.bankingsystem.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankingsystem.classes.Address;
import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.dtos.BalanceDTO;
import com.ironhack.bankingsystem.model.*;
import com.ironhack.bankingsystem.repository.*;
import com.ironhack.bankingsystem.service.impl.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Checking checking;
    private Saving saving;
    private Saving saving2;
    private CreditCard creditCard;

    private AccountHolder accountHolder;
    private AccountHolder accountHolder2;
    private AccountHolder accountHolder3;
    private AccountHolder accountHolder4;

    private Role role;
    private Role role2;
    private Role role3;
    private Role role4;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        accountHolder = new AccountHolder("Manuel Gómez", "manuelg", "1234",
                LocalDateTime.of(1990, 2, 5, 0, 0),
                new Address("Calle Benito Pérez, 10, 2A", "30254", "Madrid", "Spain")
        );
        accountHolder2 = new AccountHolder("Ana Pérez", "anap", "9876",
                LocalDateTime.of(1980, 2, 5, 0, 0),
                new Address("Calle Benito Pérez, 10, 2A", "30254", "Madrid", "Spain")
        );
        accountHolder3 = new AccountHolder("Fernando Gómez", "fernandog", "5555",
                LocalDateTime.of(1992, 2, 5, 0, 0),
                new Address("Calle Benito Pérez, 10, 2A", "30254", "Madrid", "Spain")
        );
        accountHolder4 = new AccountHolder("Raquel Pérez", "raquelp", "5555",
                LocalDateTime.of(1992, 2, 5, 0, 0),
                new Address("Calle Benito Pérez, 10, 2A", "30254", "Madrid", "Spain")
        );

        accountHolderRepository.saveAll(List.of(accountHolder, accountHolder2, accountHolder3, accountHolder4));

        role = new Role("ACCOUNT_HOLDER", accountHolder);
        role2 = new Role("ACCOUNT_HOLDER", accountHolder2);
        role3 = new Role("ACCOUNT_HOLDER", accountHolder3);
        role4 = new Role("ACCOUNT_HOLDER", accountHolder4);

        checking = new Checking(new Money(BigDecimal.valueOf(500)), accountHolder, accountHolder3, "A1B2C3");
        saving = new Saving(new Money(BigDecimal.valueOf(1500)), accountHolder, accountHolder3, "A1B2C3");
        saving2 = new Saving(new Money(BigDecimal.valueOf(3000)), accountHolder2, accountHolder4, "A1B2C3");
        creditCard = new CreditCard(new Money(BigDecimal.valueOf(1000)), accountHolder, accountHolder3);
        saving = new Saving(new Money(BigDecimal.valueOf(1500)), accountHolder, accountHolder3, "A1B2C3");

        roleRepository.saveAll(List.of(role, role2, role3, role4));
        checkingRepository.save(checking);
        savingRepository.saveAll(List.of(saving, saving2));
        creditCardRepository.save(creditCard);
    }

    @AfterEach
    public void tearDown() {
        roleRepository.deleteAll();
        creditCardRepository.deleteAll();
        savingRepository.deleteAll();
        checkingRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    public void getAccountBalance_ExistingId_Balance() throws Exception {
        List<Checking> checkings = checkingRepository.findAll();
        Long id1 = checkings.get(0).getId();
        List<Saving> savings = savingRepository.findAll();
        Long id2 = savings.get(0).getId();
        List<CreditCard> creditCards = creditCardRepository.findAll();
        Long id3 = creditCards.get(0).getId();

        MvcResult result = mockMvc.perform(get("/admin/account-balance/" + id1).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("500"));

        result = mockMvc.perform(get("/admin/account-balance/" + id2).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1500"));

        result = mockMvc.perform(get("/admin/account-balance/" + id3).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1000"));
    }

    @Test
    public void getAccountBalance_NotExistingId_Balance() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/account-balance/100000").characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void setAccountBalance_ExistingIdAndValidBalance_BalanceModified() throws Exception {
        List<Checking> checkings = checkingRepository.findAll();
        Long id1 = checkings.get(0).getId();
        List<Saving> savings = savingRepository.findAll();
        Long id2 = savings.get(0).getId();
        List<CreditCard> creditCards = creditCardRepository.findAll();
        Long id3 = creditCards.get(0).getId();

        BalanceDTO balance = new BalanceDTO(Currency.getInstance(("EUR")).getCurrencyCode(), BigDecimal.valueOf(5000));
        String body = objectMapper.writeValueAsString(balance);

        MvcResult result1 = mockMvc.perform(patch("/admin/account-balance/" + id1).content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult result2 = mockMvc.perform(patch("/admin/account-balance/" + id2).content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isNoContent())
                .andReturn();

        MvcResult result3 = mockMvc.perform(patch("/admin/account-balance/" + id3).content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isNoContent())
                .andReturn();

        assertEquals(BigDecimal.valueOf(5000).setScale(2), checkingRepository.findById(id1).get().getBalance().getAmount());
        assertEquals(BigDecimal.valueOf(5000).setScale(2), savingRepository.findById(id2).get().getBalance().getAmount());
        assertEquals(BigDecimal.valueOf(5000).setScale(2), creditCardRepository.findById(id3).get().getBalance().getAmount());
    }

    @Test
    public void setAccountBalance_NotExistingIdAndValidBalance_NotFound() throws Exception {
        BalanceDTO balance = new BalanceDTO("EUR", BigDecimal.valueOf(5000));
        String body = objectMapper.writeValueAsString(balance);

        MvcResult result = mockMvc.perform(patch("/admin/account-balance/1000000").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void setAccountBalance_ExistingIdAndNotValidBalance_BalanceModified() throws Exception {
        List<Checking> checkings = checkingRepository.findAll();
        Long id1 = checkings.get(0).getId();

        BalanceDTO balance = new BalanceDTO("ACURRENCY", BigDecimal.valueOf(5000));
        String body = objectMapper.writeValueAsString(balance);

        MvcResult result = mockMvc.perform(patch("/admin/account-balance/" + id1).content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "manuelg", password = "1234", roles = {"ACCOUNT_HOLDER"})
    public void getBalanceForAccount_ExistingIdAndCorrectPrimaryUser_Balance() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Account> accounts = accountRepository.findByPrimaryOwnerUsernameOrSecondaryOwnerUsername(
                userDetails.getUsername(), userDetails.getUsername());
        Long id1 = accounts.get(0).getId();

        MvcResult result = mockMvc.perform(get("/account/" + id1).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("500"));
    }

    @Test
    @WithMockUser(username = "fernandog", password = "5555", roles = {"ACCOUNT_HOLDER"})
    public void getBalanceForAccount_ExistingIdAndCorrectSecondaryUser_Balance() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Account> accounts = accountRepository.findByPrimaryOwnerUsernameOrSecondaryOwnerUsername(
                userDetails.getUsername(), userDetails.getUsername());
        Long id1 = accounts.get(0).getId();

        MvcResult result = mockMvc.perform(get("/account/" + id1).characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("500"));
    }

    @Test
    @WithMockUser(username = "manuelg", password = "1234", roles = {"ACCOUNT_HOLDER"})
    public void getBalanceForAccount_NotExistingIdAndCorrectUser_NotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/100000").characterEncoding("UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "anap", password = "9876", roles = {"ACCOUNT_HOLDER"})
    public void getBalanceForAccount_ExistingIdAndIncorrectUser_Forbidden() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Account> accounts = accountRepository.findByPrimaryOwnerUsernameOrSecondaryOwnerUsername(
                "manuelg", "manuelg");
        Long id1 = accounts.get(0).getId();

        MvcResult result = mockMvc.perform(get("/account/" + id1).characterEncoding("UTF-8"))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "manuelg", password = "1234", roles = {"ACCOUNT_HOLDER"})
    public void transferMoney_ValidTransaction_BalancesModifiedAndTransactionSaved() throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Account> accounts = accountRepository.findByPrimaryOwnerNameOrSecondaryOwnerName(
                "Ana Pérez", "Ana Pérez");
        Long id = accounts.get(0).getId();

        Transaction transaction = new Transaction((Account) checking, "Ana Pérez", id,
                new Money(BigDecimal.valueOf(100), checking.getBalance().getCurrency()));
        String body = objectMapper.writeValueAsString(transaction);

        MvcResult result = mockMvc.perform(patch("/transfer-money").content(body)
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(status().isNoContent())
                .andReturn();

        assertEquals(BigDecimal.valueOf(400).setScale(2),
                checkingRepository.findById(checking.getId()).get().getBalance().getAmount());
        assertEquals(BigDecimal.valueOf(3100).setScale(2),
                savingRepository.findById(id).get().getBalance().getAmount());
        assertTrue(transactionRepository.existsById(transaction.getId()));
    }
}