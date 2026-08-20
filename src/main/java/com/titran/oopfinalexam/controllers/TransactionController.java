package com.titran.oopfinalexam.controllers;

import com.titran.oopfinalexam.model.TransactionType;
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
    public ResponseEntity<List<TransactionDTO>> getTransactions(
            @RequestParam(name = "type", required = false) String type
    ) {
        TransactionType transactionType = null;
        if (type != null && !type.isBlank()) {
            transactionType = TransactionType.valueOf(type.trim().toUpperCase());
        }

        List<TransactionDTO> transactions = transactionService.getTransactionsByType(transactionType);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/accounts/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccount(@PathVariable("id") String accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> createTransaction(@RequestBody List<TransactionDTO> dto) {
        List<TransactionDTO> created = transactionService.createTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}