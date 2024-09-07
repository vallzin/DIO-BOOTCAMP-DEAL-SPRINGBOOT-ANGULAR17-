package br.com.dio;

import br.com.dio.persistence.entity.EmployeeDAO;
import br.com.dio.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@SpringBootApplication
public class Main {

	private final static EmployeeDAO employeeDAO = new EmployeeDAO();

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost/jdbc-sample","jdbc-sample","123456")
				.load();

		flyway.migrate();

		/*var employees = new EmployeeEntity();
		employees.setName("pedro");
		employees.setSalary(new BigDecimal("4800"));
		employees.setBirthday(OffsetDateTime.now().minusYears(25));
		System.out.println(employees);
		employeeDAO.insert(employees);
		System.out.println(employees);*/
	}
}
