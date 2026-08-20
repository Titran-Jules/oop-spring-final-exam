package com.titran.oopfinalexam.model.dto;

import com.titran.oopfinalexam.model.AccountType;

public record AccountDTO(
        String id,
        AccountType accountType
) {}
