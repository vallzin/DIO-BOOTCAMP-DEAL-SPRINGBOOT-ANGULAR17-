package dio.spring.security.repository;

import dio.spring.security.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<Usuario, Integer>{

    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.username= (:username)")
    public Usuario findByUsername(@Param("username") String name);
}
