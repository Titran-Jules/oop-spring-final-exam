package com.titran.oopfinalexam.controllers;

import com.titran.oopfinalexam.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{id}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("id") String accountId) {
        BigDecimal balance = accountService.calculateAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}