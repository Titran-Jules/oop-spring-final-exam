package com.titran.oopfinalexam.repository;

import com.titran.oopfinalexam.model.TransactionType;
import com.titran.oopfinalexam.model.dto.TransactionDTO;
import com.titran.oopfinalexam.utils.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    public List<TransactionDTO> createTransactions(List<TransactionDTO> transactionDTOList) {
        String sql = """
            INSERT INTO transaction_table (id, created_at, transaction_type, amount, reason, account_id)
            VALUES (?, ?, ?::transaction_type, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (TransactionDTO transactionDTO : transactionDTOList) {
                ps.setString(1, transactionDTO.id());
                ps.setTimestamp(2, Timestamp.from(transactionDTO.createdAt()));
                ps.setString(3, transactionDTO.transactionType().name());
                ps.setBigDecimal(4, transactionDTO.amount());
                ps.setString(5, transactionDTO.reason());
                ps.setString(6, transactionDTO.accountId());
                ps.executeUpdate();
            }

            return transactionDTOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransactionDTO> findByAccountId(String accountId) {
        String sql = """
            SELECT id, created_at, transaction_type, amount, reason, account_id
            FROM transaction_table
            WHERE account_id = ?
            ORDER BY created_at DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                List<TransactionDTO> transactions = new ArrayList<>();
                while (rs.next()) {
                    transactions.add(transactionRowMapper(rs));
                }
                return transactions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransactionDTO> findAllByType(TransactionType typeFilter) {
        boolean hasFilter = (typeFilter != null);
        String sql = "SELECT id, created_at, transaction_type, amount, reason, account_id FROM transaction_table"
                + (hasFilter ? " WHERE transaction_type = ?::transaction_type" : "")
                + " ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (hasFilter) {
                ps.setString(1, typeFilter.name());
            }
            try (ResultSet rs = ps.executeQuery()) {
                List<TransactionDTO> transactions = new ArrayList<>();
                while (rs.next()) {
                    transactions.add(transactionRowMapper(rs));
                }
                return transactions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BigDecimal calculateBalanceByAccountId(String accountId) {
        String sql = """
            SELECT
                COALESCE(SUM(CASE WHEN transaction_type = 'IN' THEN amount ELSE 0 END), 0) -
                COALESCE(SUM(CASE WHEN transaction_type = 'OUT' THEN amount ELSE 0 END), 0) AS balance
            FROM transaction_table
            WHERE account_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("balance");
                }
                return BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionDTO transactionRowMapper(ResultSet rs) throws SQLException {
        var transactionDto = new TransactionDTO(
                rs.getString("id"),
                rs.getTimestamp("created_at").toInstant(),
                TransactionType.valueOf(rs.getString("transaction_type")),
                rs.getBigDecimal("amount"),
                rs.getString("reason"),
                rs.getString("account_id")
        );
        return transactionDto;
    }
}
