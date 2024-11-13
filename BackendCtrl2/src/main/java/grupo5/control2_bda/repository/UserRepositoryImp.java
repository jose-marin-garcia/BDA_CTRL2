package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<User> findAll() {
        String queryText = "SELECT * FROM usuario";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            return connection.createQuery(queryText).executeAndFetch(User.class);
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener usuarios", e);
        }
    }

    @Override
    public User findById(Long id) {
        String queryText = "SELECT * FROM usuario WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            User usuario = connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
            return usuario;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener el usuario por ID", e);
        }
    }

    @Override
    public void save(User user) {
        String queryText = "INSERT INTO usuario (id, name, password, email) VALUES (:id, :name, :encodedPassword, :email)";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("id", user.getId())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String queryText = "DELETE FROM usuario WHERE id = :id";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            connection.createQuery(queryText)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el usuario", e);
        }
    }

    @Override
    public User getUsuarioByEmail(String email) {
        String queryText = "SELECT * FROM usuario WHERE email = :email";
        try (Connection connection = sql2o.open()) {
            System.out.println("Conexión exitosa a la base de datos");
            User user = connection.createQuery(queryText)
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
            return user;
        } catch (Exception e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al obtener el cliente por email", e);
        }
    }

    @Override
    public boolean existsUser(Long id){
        String queryText = "SELECT EXISTS(SELECT id FROM usuario WHERE id = :id)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("id", id);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }

    @Override
    public boolean existsUserByCorreo(String correo){
        String queryText = "SELECT EXISTS(SELECT id FROM usuario WHERE email = :correo)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("correo", correo);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }

}
