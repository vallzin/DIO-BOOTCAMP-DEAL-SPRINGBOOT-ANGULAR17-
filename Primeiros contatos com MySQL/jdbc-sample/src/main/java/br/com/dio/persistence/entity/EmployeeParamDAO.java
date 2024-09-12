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
        String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()));

            int affectedRows = statement.executeUpdate(); // Executa a inserção
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getLong(1)); // Atualiza o ID do funcionário
                    } else {
                        throw new SQLException("Falha ao obter o ID gerado.");
                    }
                }
            }
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

    public void insertBatch(final List<EmployeeEntity> entities) throws SQLException {
        // Verifica se a lista de entidades não está vazia
        if (entities == null || entities.isEmpty()) {
            System.out.println("Nenhum funcionário para inserir.");
            return;
        }

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";
            // Configura o auto-commit para false
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                int batchCount = 0;

                for (EmployeeEntity entity : entities) {
                    statement.setString(1, entity.getName());
                    statement.setBigDecimal(2, entity.getSalary());

                    // Converte o birthday para Timestamp
                    OffsetDateTime birthday = entity.getBirthday();
                    if (birthday != null) {
                        // Converte diretamente para Timestamp
                        statement.setTimestamp(3, Timestamp.valueOf(birthday.toLocalDateTime()));
                    } else {
                        statement.setNull(3, java.sql.Types.TIMESTAMP); // Define como NULL se a data for nula
                    }

                    // Adiciona a instrução ao lote
                    statement.addBatch();
                    batchCount++;

                    if (batchCount % 1000 == 0 || batchCount == entities.size()) {
                        // Executa o lote a cada 1000 registros ou no final
                        statement.executeBatch();
                    }
                }

                // Confirma as alterações no banco de dados
                connection.commit();
                System.out.printf("Inseridos %d funcionários.%n", entities.size());
            } catch (SQLException e) {
                // Se ocorrer um erro, faz rollback
                connection.rollback();
                System.err.println("Erro ao inserir funcionários: " + e.getMessage());
                e.printStackTrace();
                throw e; // Retorna a exceção para o chamador
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw e; // Retorna a exceção para o chamador
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
        String sql = "SELECT e.id AS employee_id,\n"
                    + " e.name,\n"
                    + " e.salary,\n"
                    + " e.birthday,\n"
                    + " c.id AS contact_id,\n"
                    + " c.description,\n"
                    + " c.type\n"
                    + "FROM employees e\n"
                    + "LEFT JOIN contacts c\n"
                    + "ON c.employee_id = e.id "
                    + "WHERE e.id = ?";
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
                    entity.setContact(new ContactEntity());
                    entity.getContact().setId(resultSet.getLong("contact_id"));
                    entity.getContact().setDescription(resultSet.getString("description"));
                    entity.getContact().setType(resultSet.getString("type"));
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