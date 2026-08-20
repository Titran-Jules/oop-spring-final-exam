package com.titran.oopfinalexam.repository;

import com.titran.oopfinalexam.model.AccountType;
import com.titran.oopfinalexam.model.dto.AccountDTO;
import com.titran.oopfinalexam.utils.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class AccountRepository {

    public AccountDTO createAccount (AccountDTO accountDTO) {
        String sql = "INSERT INTO account (id, account_type) VALUES (?, ?::account_type)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountDTO.id());
            ps.setString(2, accountDTO.accountType().name());
            ps.executeUpdate();
            return accountDTO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<AccountDTO> findById(String id) {
        String sql = "SELECT id, account_type FROM account WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(accountRowMapper(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT 1 FROM account WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountDTO accountRowMapper(ResultSet rs) throws SQLException {
        return new AccountDTO(
                rs.getString("id"),
                AccountType.valueOf(rs.getString("account_type"))
        );
    }
}
