package br.com.dio;

import br.com.dio.persistence.EmployeeAuditDAO;
import br.com.dio.persistence.entity.*;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Locale;

import net.datafaker.Faker;

import java.util.Random;

@SpringBootApplication
public class Main {

//	private final static EmployeeDAO employeeDAO = new EmployeeDAO();
	private final static Random random = new Random();
	private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
	private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
	private final static Faker faker = new Faker ( new Locale("pt","BR"));
	private final static ContactDAO contactDAO = new ContactDAO();

	public static void main(String[] args) throws SQLException {
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

//		 Inserir um novo funcionário
//		var insert = new EmployeeEntity();
//		insert.setName("Miguel'");
//		insert.setSalary(new BigDecimal("3200"));
//		insert.setBirthday(OffsetDateTime.now().minusYears(21));
//		System.out.println(insert);
//		employeeDAO.insertWithProcedure(insert); // Chamada do método de inserção
//		System.out.println(insert);

		// Inserir um novo funcionário
//		EmployeeEntity employee1 = new EmployeeEntity();
//		employee1.setName("jose");
//		employee1.setSalary(new BigDecimal("3200"));
//		employee1.setBirthday(OffsetDateTime.now().minusYears(21));
//		employeeDAO.insertWithProcedure(employee1);
//		System.out.println("ID do funcionário inserido: " + employee1.getId());

		// Inserir outro funcionário
//		EmployeeEntity employee2 = new EmployeeEntity();
//		employee2.setName("felipe");
//		employee2.setSalary(new BigDecimal("4000"));
//		employee2.setBirthday(OffsetDateTime.now().minusYears(25));
//		employeeDAO.insertWithProcedure(employee2);
//		System.out.println("ID do funcionário inserido: " + employee2.getId());

		// Verificar todos os funcionários
//		List<EmployeeEntity> employees = employeeDAO.findAll();
//		employees.forEach(emp -> System.out.println("Funcionário: " + emp.getName() + ", ID: " + emp.getId()));

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

		/*
		var entities = Stream.generate( () ->{
			var employee = new EmployeeEntity();
			employee.setName(faker.name().fullName());
			employee.setSalary(new BigDecimal(faker.number().digits(4)));
//			// Gera uma data de aniversário aleatória entre 18 e 65 anos
//			int age = random.nextInt(48) + 18; // Gera uma idade entre 18 e 65
//			LocalDate birthdayLocalDate = LocalDate.now().minusYears(age);
//			OffsetDateTime birthday = OffsetDateTime.of(birthdayLocalDate, LocalTime.MIN, ZoneOffset.UTC);
//			employee.setBirthday(birthday);
			employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40,20)), LocalTime.MIN, ZoneOffset.UTC));
			return employee;
		}).limit(10000).toList();

		employeeDAO.insertBatch(entities);
		*/

		/*
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeEntity employee = new EmployeeEntity();

		employee.setName("Antonio");
		employee.setSalary(new BigDecimal("3200"));
		employee.setBirthday(OffsetDateTime.now().minusYears(21));

		employeeDAO.insert(employee); // Chamada do método de inserção

		// Verifique se o ID foi atribuído corretamente
		if (employee.getId() <= 0) {
			throw new IllegalStateException("Erro: O ID do funcionário não foi gerado.");
		}

		System.out.println("Funcionário inserido com ID: " + employee.getId());
		*/

//		var employee = new EmployeeEntity();
//		employee.setName("jojo");
//		employee.setSalary(new BigDecimal("1200"));
//		employee.setBirthday(OffsetDateTime.now().minusYears(25));
//		employeeDAO.insert(employee); // Chamada do método de inserção
//
//// 		Verifique se o ID foi atribuído corretamente
//		if (employee.getId() <= 0) {
//			throw new IllegalStateException("Erro: O ID do funcionário não foi gerado.");
//		}

//		System.out.println("Funcionário inserido com ID: " + employee.getId());

//		// Criar um novo contato
//		var contact = new ContactEntity(); // Use ContactEntity aqui
//		contact.setDescription("antonio@ant.com");
//		contact.setType("E-mail");
//		contact.setEmployee(employee); // Associa o contato ao funcionário
//
//// 		Inserir o contato
//		contactDAO.insert(contact);
//		System.out.println("Contato inserido: " + contact);

		System.out.println(employeeDAO.findById(1));

	}


}





