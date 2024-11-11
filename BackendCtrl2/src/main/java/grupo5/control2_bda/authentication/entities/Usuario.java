package grupo5.control2_bda.authentication.entities;

import grupo5.control2_bda.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private String password;
    private static String ROLE = "ROLE_CLIENTE";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public static Usuario userToUsuario(User user) {
        return new Usuario(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}