package grupo5.control2_bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaVencimiento;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCompletada;
    private Integer id_usuario;
}
