package com.titran.oopfinalexam.controllers;

import com.titran.oopfinalexam.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts/{id}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("id") String accountId) {
        BigDecimal balance = accountService.calculateAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}