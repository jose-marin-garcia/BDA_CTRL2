package grupo5.control2_bda.service;


import grupo5.control2_bda.models.User;
import grupo5.control2_bda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String encodedPassword = generateEncodedPassword(usuario.getPassword());
        usuario.setPassword(encodedPassword);
        validateUsuario(usuario);
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    private String generateEncodedPassword(String passsword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(passsword);
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

    private void validateUsuario(User user){
        Long id = user.getId();
        String correo = user.getEmail();

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser nulo o vacío");
        }

        if(id != null && !existsUser(id)){
            throw new IllegalArgumentException("No existe el voluntario");
        }

        if(correo != null){
            if(existsUserByCorreo(correo)){
                throw new IllegalArgumentException("El correo ingresado ya esta registrado");
            }

            if(!isValidCorreo(correo)){
                throw new IllegalArgumentException("El correo ingresado no es valido");
            }
        }
    }

    public boolean existsUser(Long idVoluntario){
        return usuarioRepository.existsUser(idVoluntario);
    }

    public boolean existsUserByCorreo(String correo){
        return usuarioRepository.existsUserByCorreo(correo);
    }

    private boolean isValidCorreo(String correo){
        System.out.println("Validando correo: " + correo);
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        boolean isValid = matcher.matches();
        System.out.println("Correo válido: " + isValid);
        return isValid;
    }

}
