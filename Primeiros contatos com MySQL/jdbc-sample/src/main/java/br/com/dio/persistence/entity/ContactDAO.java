package br.com.dio.persistence.entity;

import br.com.dio.persistence.ConnectionUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class ContactDAO {

    public void insert(final ContactEntity entity) {
        // Verifique se o funcionário é válido antes de inserir
        if (entity.getEmployee() == null || entity.getEmployee().getId() <= 0) {
            throw new IllegalArgumentException("Funcionário inválido.");
        }

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO contacts (description, type, employee_id) VALUES (?, ?, ?)")) {

            statement.setString(1, entity.getDescription());
            statement.setString(2, entity.getType());
            statement.setLong(3, entity.getEmployee().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // Aqui você pode lançar uma exceção customizada ou registrar o erro
            System.err.println("Erro ao inserir contato: " + e.getMessage());
            e.printStackTrace();
        }
    }
}