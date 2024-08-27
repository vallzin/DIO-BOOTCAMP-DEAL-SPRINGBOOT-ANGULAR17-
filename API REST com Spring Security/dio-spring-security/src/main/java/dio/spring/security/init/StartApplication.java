package dio.spring.security.init;

import dio.spring.security.model.Usuario;
import dio.spring.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    @Transactional
//    public void run(String ... args) throws Exception{
//
//        Usuario user = repository.findByUsername("admin");
//
//        if(user == null){
//
//            user = new Usuario();
//            user.setName("ADMIN");
//            user.setUsername("admin");
//            user.setPassword("adm123");
//            user.setRoles(Collections.singletonList("MANAGERS"));
//            repository.save(user);
//        }
//
//        user = repository.findByUsername("user");
//
//        if (user == null){
//
//            user = new Usuario();
//            user.setName("USER");
//            user.setUsername("user");
//            user.setPassword("user123");
//            user.setRoles(Collections.singletonList("USERS"));
//            repository.save(user);
//        }
//    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createUserIfNotFound("admin", "ADMIN", "adm123", "MANAGERS");
        createUserIfNotFound("user", "USER", "user123", "USERS");
    }

    private void createUserIfNotFound(String username, String name, String password, String role) {
        Usuario user = repository.findByUsername(username);
        if (user == null) {
            user = new Usuario();
            user.setName(name);
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Collections.singletonList(role));
            repository.save(user);
        }
    }

}
