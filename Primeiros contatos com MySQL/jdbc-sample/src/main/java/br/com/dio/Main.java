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
				.dataSource("jdbc:mysql://localhost/jdbc-sample", "jdbc-sample", "123456")
				.baselineOnMigrate(true)// Adicione esta linha
//				.cleanDisabled(false)
//				.outOfOrder(true) // Permite migrações fora de ordem
//				.ignoreMigrationPatterns("*:ignored") // Ignora migrações não aplicadas
				.load();

//		flyway.clean();
//		flyway.repair();
//		flyway.baseline();
		flyway.migrate();

		var insert = new EmployeeEntity();
		insert.setName("Augustus");
		insert.setSalary(new BigDecimal("8800"));
		insert.setBirthday(OffsetDateTime.now().minusYears(23));
		System.out.println(insert);
		employeeDAO.insert(insert);
		System.out.println(insert);


//		employeeDAO.findAll().forEach(System.out::println);

//		System.out.println(employeeDAO.findById(1));


		/*var update = new EmployeeEntity();
//		update.setId(insert.getId());
		update.setId(3);
		update.setName("lulu");
		update.setSalary(new BigDecimal("1000"));
		update.setBirthday(OffsetDateTime.now().minusDays(39));
		employeeDAO.update(update);*/


		employeeDAO.delete(4);

	}
}
