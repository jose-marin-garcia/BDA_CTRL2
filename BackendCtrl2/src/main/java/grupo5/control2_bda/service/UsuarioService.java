package grupo5.control2_bda.service;


import grupo5.control2_bda.models.User;
import grupo5.control2_bda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    @Autowired
    private UserRepository usuarioRepository;

    public List<User> getAllUsuarios() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de usuarios", e);
        }
    }

    public User getUsuarioById(Long id) {
        try {
            User usuario = usuarioRepository.findById(id);
            if (usuario != null) {
                return usuario;
            } else{
                throw new RuntimeException("Usuario no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario", e);
        }
    }

    public void createUsuario(User usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    public void updateUsuario(User usuario) {
        try {
            getUsuarioById(usuario.getId());
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    public void deleteUsuario(Long id) {
        try {
            getUsuarioById(id);
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario", e);
        }
    }

    public User getUserByEmail(String email) {
        try {
            User cliente = usuarioRepository.getUsuarioByEmail(email);
            if (cliente != null) {
                return cliente;
            } else {
                throw new RuntimeException("Cliente no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Cliente", e);
        }
    }

}
