package grupo5.control2_bda.controller;

import grupo5.control2_bda.models.User;
import grupo5.control2_bda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsuarios() {
        try {
            List<User> usuarios = usuarioService.getAllUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioById(@PathVariable Long id) {
        try {
            User usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody User usuario) {
        try {
            usuarioService.createUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable Long id, @RequestBody User usuario) {
        try {
            usuario.setId(id);
            usuarioService.updateUsuario(usuario);
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el usuario");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el usuario");
        }
    }
}
