package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class NotificacionRepositoryImp implements NotificacionRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Notificacion> findAll() {
        String queryText = "SELECT * FROM notificacion";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText).executeAndFetch(Notificacion.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener notificaciones", e);
        }
    }

    @Override
    public Notificacion findById(Long id) {
        String queryText = "SELECT * FROM notificacion WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            Notificacion notificacion = connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Notificacion.class);
            return notificacion;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener la notificación por ID", e);
        }
    }

    @Override
    public void save(Notificacion notificacion) {
        String queryText = "INSERT INTO notificacion (id_usuario, id_tarea, fechaEnvio, tipo) VALUES (:usuarioId, :tareaId, :fechaEnvio, :tipo)";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("usuarioId", notificacion.getId_usuario())
                    .addParameter("tareaId", notificacion.getId_tarea())
                    .addParameter("fechaEnvio", notificacion.getFechaEnvio())
                    .addParameter("tipo", notificacion.getTipo())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al crear la notificación", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String queryText = "DELETE FROM notificacion WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar la notificación", e);
        }
    }

}
