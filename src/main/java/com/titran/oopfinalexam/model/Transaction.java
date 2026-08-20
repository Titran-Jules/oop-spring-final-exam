package com.titran.oopfinalexam.model;

import java.math.BigDecimal;
import java.time.Instant;

public record Transaction(
        String id,
        Instant createdAt,
        TransactionType transactionType,
        BigDecimal amount,
        String reason
) {}
