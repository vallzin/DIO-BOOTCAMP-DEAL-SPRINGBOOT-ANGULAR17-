package br.com.dio.persistence.entity;

import br.com.dio.persistence.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity) {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            // Corrigindo a formatação da query
            var sql = "INSERT INTO employees (name, salary, birthday) VALUES ('"
                    + entity.getName() + "', "
                    + entity.getSalary().toString() + ", '"
                    + formatOffsetDateTime(entity.getBirthday()) + "')";

            // Executando a inserção
//            System.out.println("SQL: "+ sql);
            int affectedRows = statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados%n", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(final EmployeeEntity entity){
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ){
            var sql = "UPDATE employees set "
                    + "name     = '" + entity.getName()+ "',"
                    + "salary   = " + entity.getSalary().toString() +  ","
                    + "birthday = '" + formatOffsetDateTime(entity.getBirthday()) + "'"
                    + "WHERE id = " + entity.getId();
            int affectedRows = statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void delete(final long id){
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ){
            var sql = "DELETE FROM employees WHERE id = " + id;
            int affectedRows = statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public List<EmployeeEntity> findAll(){

        List<EmployeeEntity> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet affectedRows = statement.executeQuery("SELECT * FROM employees ORDER BY name");
            var resultSet = statement.getResultSet();
            while(resultSet.next()){
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birtdayInstante = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birtdayInstante, UTC));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet affectedRows = statement.executeQuery("SELECT * FROM employees WHERE id = "+id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birtdayInstante = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birtdayInstante, UTC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public String formatOffsetDateTime(final OffsetDateTime dateTime){
        var utcDatetime = dateTime.withOffsetSameInstant(UTC);
//        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return utcDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
