package grupo5.control2_bda.authentication.service;

import grupo5.control2_bda.authentication.entities.Usuario;
import grupo5.control2_bda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    public Usuario getUserByEmail(String email) {
        return Usuario.userToUsuario(usuarioService.getUserByEmail(email));
    }

}
