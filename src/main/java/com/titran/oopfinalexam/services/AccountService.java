package com.titran.oopfinalexam.services;

import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.repository.AccountRepository;
import com.titran.oopfinalexam.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal calculateAccountBalance(String accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction tx : transactions) {
            if ("IN".equalsIgnoreCase(tx.getTransactionType())) {
                balance = balance.add(tx.getAmount());
            } else if ("OUT".equalsIgnoreCase(tx.getTransactionType())) {
                balance = balance.subtract(tx.getAmount());
            }
        }
        return balance;
    }
}