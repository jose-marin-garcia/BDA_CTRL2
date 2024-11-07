package grupo5.control2_bda.service;

import grupo5.control2_bda.models.Notificacion;
import grupo5.control2_bda.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> getAllNotificaciones() {
        try {
            return notificacionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de notificaciones", e);
        }
    }

    public Notificacion getNotificacionById(Long id) {
        try {
            Notificacion notificacion = notificacionRepository.findById(id);
            if (notificacion != null) {
                return notificacion;
            } else{
                throw new RuntimeException("Notificación no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la notificación", e);
        }
    }

    public void createNotificacion(Notificacion notificacion) {
        try {
            notificacionRepository.save(notificacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la notificación", e);
        }
    }

    public void updateNotificacion(Notificacion notificacion) {
        try {
            getNotificacionById(notificacion.getId());
            notificacionRepository.save(notificacion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la notificación", e);
        }
    }

    public void deleteNotificacion(Long id) {
        try {
            getNotificacionById(id);
            notificacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la notificación", e);
        }
    }
}
