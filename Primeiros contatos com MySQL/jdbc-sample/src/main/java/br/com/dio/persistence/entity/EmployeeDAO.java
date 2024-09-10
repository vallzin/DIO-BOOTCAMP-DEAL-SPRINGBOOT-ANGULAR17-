package br.com.dio.persistence.entity;

import br.com.dio.persistence.ConnectionUtil;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity) {
        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(formatOffsetDateTime(entity.getBirthday())));
            int affectedRows = statement.executeUpdate();
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void insert(final EmployeeEntity entity) {
//        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";
//        try (Connection connection = ConnectionUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, entity.getName());
//            statement.setBigDecimal(2, entity.getSalary());
//            statement.setTimestamp(3, Timestamp.valueOf(formatOffsetDateTime(entity.getBirthday())));
//            int affectedRows = statement.executeUpdate();
//
//            // Obtendo o ID gerado
////            if (affectedRows > 0) {
////                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
////                    if (generatedKeys.next()) {
////                        entity.setId(generatedKeys.getLong(1)); // Atribuindo o ID gerado ao objeto
////                    }
////                }
////            }
//            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void update(final EmployeeEntity entity) {
        String sql = "UPDATE employees SET name = ?, salary = ?, birthday = ? WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(formatOffsetDateTime(entity.getBirthday())));
            statement.setLong(4, entity.getId());
            int affectedRows = statement.executeUpdate();
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, ZoneOffset.UTC));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id) {
        EmployeeEntity entity = null;
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    entity = new EmployeeEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setSalary(resultSet.getBigDecimal("salary"));
                    var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                    entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, ZoneOffset.UTC));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String formatOffsetDateTime(final OffsetDateTime dateTime) {
        return dateTime.withOffsetSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
