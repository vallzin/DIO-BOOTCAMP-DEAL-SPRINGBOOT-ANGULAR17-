package dio.my_first.web_api.controller;

import dio.my_first.web_api.model.Usuario;
import dio.my_first.web_api.repository.UsearioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsearioRepository repository;
    @GetMapping()
    public List<Usuario>getUsers(){
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public Usuario getOne(@PathVariable("username") String username){
        return repository.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        repository.deleteById(id);
    }

    @PostMapping()
    public void postUser(@RequestBody Usuario usuario){
        repository.save(usuario);
    }

    @PutMapping
    public void putUser(@RequestBody Usuario usuario){
        repository.save(usuario);
    }
}
