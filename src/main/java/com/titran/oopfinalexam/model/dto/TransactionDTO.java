package com.titran.oopfinalexam.model.dto;

import com.titran.oopfinalexam.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDTO(
        String id,
        Instant createdAt,
        TransactionType transactionType,
        BigDecimal amount,
        String reason,
        String account_id
) {
}
