package com.titran.oopfinalexam.services;

import com.titran.oopfinalexam.model.Transaction;
import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.repository.AccountRepository;
import com.titran.oopfinalexam.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BigDecimal calculateAccountBalance(String accountId) {
        try {
            if (!accountRepository.existsById(accountId)) {
                throw new AccountNotFoundException("Account with ID " + accountId + " not found");
            }

            return transactionRepository.calculateBalanceByAccountId(accountId);

        } catch (AccountNotFoundException e) {
            throw new RuntimeException("Database error while fetching balance", e);
        }
    }
}