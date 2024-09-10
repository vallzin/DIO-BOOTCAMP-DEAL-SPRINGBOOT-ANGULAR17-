package br.com.dio;

import br.com.dio.persistence.EmployeeAuditDAO;
import br.com.dio.persistence.entity.EmployeeDAO;
import br.com.dio.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@SpringBootApplication
public class Main {

	private final static EmployeeDAO employeeDAO = new EmployeeDAO();
	private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost/jdbc-sample", "jdbc-sample", "123456")
				.baselineOnMigrate(true)// Adicione esta linha
				.cleanDisabled(false)
				.outOfOrder(true) // Permite migrações fora de ordem
				.ignoreMigrationPatterns("*:ignored") // Ignora migrações não aplicadas
				.load();

		flyway.clean();
		flyway.repair();
		flyway.baseline();
		flyway.migrate();

		// Inserir um novo funcionário
		var insert = new EmployeeEntity();
		insert.setName("thiago");
		insert.setSalary(new BigDecimal("3200"));
		insert.setBirthday(OffsetDateTime.now().minusYears(21));
		employeeDAO.insert(insert); // Chamada do método de inserção
		System.out.println("Inserindo: " + insert);

		// Inserir um novo funcionário
		EmployeeEntity employee1 = new EmployeeEntity();
		employee1.setName("jose");
		employee1.setSalary(new BigDecimal("3200"));
		employee1.setBirthday(OffsetDateTime.now().minusYears(21));
		employeeDAO.insert(employee1);
		System.out.println("ID do funcionário inserido: " + employee1.getId());

		// Inserir outro funcionário
		EmployeeEntity employee2 = new EmployeeEntity();
		employee2.setName("felipe");
		employee2.setSalary(new BigDecimal("4000"));
		employee2.setBirthday(OffsetDateTime.now().minusYears(25));
		employeeDAO.insert(employee2);
		System.out.println("ID do funcionário inserido: " + employee2.getId());

		// Verificar todos os funcionários
//		List<EmployeeEntity> employees = employeeDAO.findAll();
//		employees.forEach(emp -> System.out.println("Funcionário: " + emp.getName() + ", ID: " + emp.getId()));
	}


//		employeeDAO.findAll().forEach(System.out::println);

//		System.out.println(employeeDAO.findById(1));


//		var update = new EmployeeEntity();
//		update.setId(insert.getId());
////		update.setId(3);
//		update.setName("Raphael");
//		update.setSalary(new BigDecimal("5000"));
//		update.setBirthday(OffsetDateTime.now().minusYears(50).minusDays(39));
//		employeeDAO.update(update);

//		employeeDAO.delete(insert.getId());

//		employeeAuditDAO.findAll().forEach(System.out::println);

}
