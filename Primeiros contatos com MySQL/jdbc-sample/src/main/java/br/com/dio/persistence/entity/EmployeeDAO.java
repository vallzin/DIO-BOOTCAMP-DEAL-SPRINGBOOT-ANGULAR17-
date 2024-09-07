package br.com.dio.persistence.entity;

import br.com.dio.persistence.ConnectionUtil;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity){
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
            ){
            var sql = "INSERT INTO employees (name, salary, birthday) VALUES (' "
                    + entity.getName()+ "', "
                    + entity.getSalary().toString()
                    +", '"+ formatOffsetDateTime(entity.getBirthday())
                    + "')";
            int affectedRows = statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
//            entity.setId(statement.getGeneratedKeys().getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(final EmployeeEntity entity){};
    public void delete(final long id){};
    public List<EmployeeEntity> findAll(){
        return null;
    }
    public EmployeeEntity findById(final long id){
        return null;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
