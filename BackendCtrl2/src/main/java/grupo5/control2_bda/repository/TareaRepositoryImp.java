package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Tarea> findAllWithLimitAndOffset(int limit, int offset) {
        String queryText = "SELECT * FROM tarea LIMIT :limit OFFSET :offset";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText)
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener tareas", e);
        }
    }

    @Override
    public List<Tarea> findAllByIdUser(int id){
        String queryText = "SELECT * FROM tarea WHERE id_usuario = :id";
        try (Connection connection = sql2o.open()){
            System.out.println("Conexion existosa a la base de datos");
            return  connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e){
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener tareas", e);
        }
    }

    @Override
    public Tarea findById(Long id) {
        String queryText = "SELECT * FROM tarea WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            Tarea tarea = connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Tarea.class);
            return tarea;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener la tarea por ID", e);
        }
    }

    @Override
    public List<Tarea> findExpiringTareasByIdUser(int id) {
        String queryText = "SELECT * FROM tarea WHERE id_usuario = :id AND " +
                "fechavencimiento BETWEEN NOW() AND NOW() + '7 days' AND " +
                "estado = 'pendiente' " +
                "ORDER BY fechavencimiento ASC";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexion exitosa a la base de datos");
            return connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.err.println("Error en la conexion a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener tareas", e);
        }
    }

    @Override
    public void save(Tarea tarea) {
        String queryText = "INSERT INTO tarea (titulo, descripcion, fechaVencimiento, estado, fechaCreacion, fechaCompletada, id_usuario) VALUES (:titulo, :descripcion, :fechaVencimiento, :estado, :fechaCreacion, :fechaCompletada, :usuarioId)";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("titulo", tarea.getTitulo())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaVencimiento", tarea.getFechaVencimiento())
                    .addParameter("estado", tarea.getEstado())
                    .addParameter("fechaCreacion", tarea.getFechaCreacion())
                    .addParameter("fechaCompletada", tarea.getFechaCompletada())
                    .addParameter("usuarioId", tarea.getId_usuario())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al crear la tarea", e);
        }
    }

    @Override
    public void updateTarea(Tarea tarea) {
        String queryText = "UPDATE tarea SET titulo = :titulo, descripcion = :descripcion, fechaVencimiento = :fechaVencimiento, " +
                "estado = :estado WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            connection.createQuery(queryText)
                    .addParameter("titulo", tarea.getTitulo())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaVencimiento", tarea.getFechaVencimiento())
                    .addParameter("estado", tarea.getEstado())
                    .addParameter("id", tarea.getId())
                    .executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la tarea", e);
        }
    }

    @Override
    public void completeTarea(Long id) {
        String queryText = "UPDATE tarea SET estado = 'completada' WHERE id = :id";
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeUpdate();
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error al completar la tarea", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String queryText = "DELETE FROM tarea WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }

    @Override
    public long getTotalCount(){
        String queryText = "SELECT COUNT(*) FROM tarea";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText).executeScalar(Long.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener el total de tareas", e);
        }
    }

    @Override
    public List<Tarea> getTareaByString(String string, Long id_usuario) {
        String queryText = "SELECT * FROM tarea WHERE id_usuario = :id_usuario AND (titulo LIKE :string OR descripcion LIKE :string)";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText)
                    .addParameter("string", "%" + string + "%")
                    .addParameter("id_usuario", id_usuario)
                    .executeAndFetch(Tarea.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener tareas", e);
        }
    }

}
