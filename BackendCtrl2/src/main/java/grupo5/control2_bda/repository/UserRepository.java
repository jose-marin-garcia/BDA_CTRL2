package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    void save(User usuario);
    public boolean existsUser(Long id);
    public boolean existsUserByCorreo(String correo);
    void deleteById(Long id);
    public User getUsuarioByEmail(String email);
}