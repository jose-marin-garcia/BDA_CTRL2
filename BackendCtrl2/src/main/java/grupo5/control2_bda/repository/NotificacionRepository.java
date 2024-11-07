package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.Notificacion;

import java.util.List;

public interface NotificacionRepository {
    List<Notificacion> findAll();
    Notificacion findById(Long id);
    void save(Notificacion notificacion);
    void deleteById(Long id);
}
