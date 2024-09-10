package br.com.dio.persistence;

import br.com.dio.persistence.entity.EmployeeAuditEntity;
import br.com.dio.persistence.entity.OperationEnum;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

public class EmployeeAuditDAO {

    public List<EmployeeAuditEntity> findAll() {
        List<EmployeeAuditEntity> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM view_employees_audit");
            while (resultSet.next()) {

                EmployeeAuditEntity entity = new EmployeeAuditEntity(
                        resultSet.getLong("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("old_name"),
                        resultSet.getBigDecimal("salary"),
                        resultSet.getBigDecimal("old_salary"),
                        getDateTimeOrNull(resultSet,"birthday"),
                        getDateTimeOrNull(resultSet,"old_birthday"),
                        OperationEnum.getByDbOperation(resultSet.getString("operation"))
                );
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public OffsetDateTime getDateTimeOrNull(final ResultSet resultSet, final String field) throws SQLException {

        return isNull(resultSet.getTimestamp(field)) ? null :
                OffsetDateTime.ofInstant(resultSet.getTimestamp(field).toInstant(),UTC);

    }

}
