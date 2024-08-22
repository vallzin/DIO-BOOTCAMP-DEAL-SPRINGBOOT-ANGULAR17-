package dio.spring.secutiry;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }
    @GetMapping("/users")public String users(){
        return "Authorizes user";
    }
    @GetMappingpublic String mangers(){
        return "Authorized manager";
    }

}
