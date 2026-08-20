package com.titran.oopfinalexam.services;

import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.repository.AccountRepository;
import com.titran.oopfinalexam.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BigDecimal calculateAccountBalance(String accountId) {
        List<TransactionDTO> transactions = transactionRepository.findByAccountId(accountId);

        BigDecimal balance = BigDecimal.ZERO;
        for (TransactionDTO tx : transactions) {
            if ("IN".equalsIgnoreCase(tx.transactionType().name())) {
                balance = balance.add(tx.amount());
            } else if ("OUT".equalsIgnoreCase(tx.transactionType().name())) {
                balance = balance.subtract(tx.amount());
            }
        }
        return balance;
    }
}