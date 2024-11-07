package grupo5.control2_bda.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    private Long id;
    private Integer id_usuario;
    private Integer id_tarea;
    private LocalDateTime fechaEnvio;
    private String tipo;
}
