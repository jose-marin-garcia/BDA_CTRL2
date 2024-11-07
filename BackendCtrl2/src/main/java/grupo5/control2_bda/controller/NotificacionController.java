package grupo5.control2_bda.controller;

import grupo5.control2_bda.models.Notificacion;
import grupo5.control2_bda.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/notificacion")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> getAllNotificaciones() {
        try {
            List<Notificacion> notificaciones = notificacionService.getAllNotificaciones();
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        try {
            Notificacion notificacion = notificacionService.getNotificacionById(id);
            return ResponseEntity.ok(notificacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createNotificacion(@RequestBody Notificacion notificacion) {
        try {
            notificacionService.createNotificacion(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Notificación creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la notificación");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        try {
            notificacion.setId(id);
            notificacionService.updateNotificacion(notificacion);
            return ResponseEntity.ok("Notificación actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la notificación");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificacion(@PathVariable Long id) {
        try {
            notificacionService.deleteNotificacion(id);
            return ResponseEntity.ok("Notificación eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la notificación");
        }
    }
}

