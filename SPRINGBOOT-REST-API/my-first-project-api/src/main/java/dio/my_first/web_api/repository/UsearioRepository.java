package dio.my_first.web_api.repository;

import dio.my_first.web_api.handler.BusinessException;
import dio.my_first.web_api.handler.CampoObrigatorioException;
import dio.my_first.web_api.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsearioRepository {

    public void save(Usuario usuario){

        if(usuario.getLogin() == null)
            throw new CampoObrigatorioException("login");

        if(usuario.getPassword() == null)
            throw new CampoObrigatorioException("password");

        if(usuario.getId() == null)
            System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        else
            System.out.println("UPDATE - Recebendo o usuário na camada de repositório");

        System.out.println(usuario);
    }

    public void deleteById(Integer id){
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para localizar um usuário", id));
        System.out.println(id);
    }

    public List<Usuario> findAll(){
        System.out.println("LIST - listando os usuários do sistema");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Vallzin","password"));
        usuarios.add(new Usuario("frank","masterpass"));
        return usuarios;
    }

    public Usuario findById(Integer id){
        System.out.println(String.format("FIND/id - Recebendo o id: %d para localizar um usuário", id));
        return new Usuario("Vallzin","password");
    }

    public Usuario findByUsername(String username){
        System.out.println(String.format("FIND/username - Recebendo o username: %S para localizar um usuário", username));
        return new Usuario("Vallzin","password");
    }
}
