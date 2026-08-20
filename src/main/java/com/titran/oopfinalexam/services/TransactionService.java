package com.titran.oopfinalexam.services;

import com.titran.oopfinalexam.model.TransactionType;
import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<TransactionDTO> getTransactionsByType(TransactionType type) {
        return transactionRepository.findAllByType(type);
    }

    public List<TransactionDTO> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public List<TransactionDTO> createTransaction(List<TransactionDTO> transactionDTOS) {
        return transactionRepository.createTransactions(transactionDTOS);
    }
}