package com.titran.oopfinalexam.services;

import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByType(type.toUpperCase());
    }

    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(TransactionDTO dto) {
        return transactionRepository.save(dto);
    }
}