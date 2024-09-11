package br.com.dio.persistence.entity;

import br.com.dio.persistence.ConnectionUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.TimeZone.LONG;

public class EmployeeParamDAO {

    public void insert(final EmployeeEntity entity) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)")) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));
            int affectedRows = statement.executeUpdate(); // Definindo a variável aqui
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertWithProcedure(final EmployeeEntity entity) {
        try (Connection connection = ConnectionUtil.getConnection();
             CallableStatement statement = connection.prepareCall("call prc_insert_employees(?, ?, ?, ?)")) {

            // Registrando o parâmetro de saída corretamente
            statement.registerOutParameter(1, Types.BIGINT); // Supondo que o ID é do tipo BIGINT
            statement.setString(2, entity.getName());
            statement.setBigDecimal(3, entity.getSalary());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));

            // Executando a chamada
            statement.executeUpdate();

            // Obtendo o ID gerado
            entity.setId(statement.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final EmployeeEntity entity) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE employees SET name = ?, salary = ?, birthday = ? WHERE id = ?")) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));
            statement.setLong(4, entity.getId());
            int affectedRows = statement.executeUpdate(); // Definindo a variável aqui
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?")) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity> entities = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY name";
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id) {
        EmployeeEntity entity = null;
        String sql = "SELECT * FROM employees WHERE id = ?"; // Corrigindo a string SQL
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) { // Usando a string SQL aqui
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) { // Executando apenas uma vez
                if (resultSet.next()) {
                    entity = new EmployeeEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setSalary(resultSet.getBigDecimal("salary"));
                    var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                    entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String formatOffsetDateTime(final OffsetDateTime dateTime) {
        return dateTime.withOffsetSameInstant(UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}