package com.titran.oopfinalexam.controllers;

import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@RequestParam("type") String type) {
        List<Transaction> transactions = transactionService.getTransactionsByType(type);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/accounts/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable("id") String accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) {
        Transaction created = transactionService.createTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}