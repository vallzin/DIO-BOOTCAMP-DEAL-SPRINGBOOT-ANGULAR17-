package dio.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }

    @GetMapping("/login")
    public String login() {
        return "Login page";
    }

    @GetMapping("/users")
    public String users(){
        return "Authorize user";
    }

    @GetMapping("/managers")
    public String mangers(){
        return "Authorized manager";
    }
}
