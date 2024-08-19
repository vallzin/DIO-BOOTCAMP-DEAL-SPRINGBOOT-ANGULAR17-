package dio.my_first.web_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MyFirstProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApiApplication.class, args);
	}

}
