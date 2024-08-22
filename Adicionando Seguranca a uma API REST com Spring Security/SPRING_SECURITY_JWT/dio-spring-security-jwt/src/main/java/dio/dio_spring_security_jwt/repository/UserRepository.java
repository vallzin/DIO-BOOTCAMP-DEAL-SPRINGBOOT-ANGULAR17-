package dio.dio_spring_security_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import dio.dio_spring_security_jwt.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT e FROM User e JSON FETCH e.roles WHERE e.username= (:username)")
    public User findByUserName(@Param("usermane") String username);

    boolean existsBYUserName(String username);
}
