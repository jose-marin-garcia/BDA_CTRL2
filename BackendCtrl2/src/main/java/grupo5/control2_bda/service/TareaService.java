package grupo5.control2_bda.service;

import grupo5.control2_bda.models.Tarea;
import grupo5.control2_bda.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getAllTareas(int limit, int offset) {
        try {
            return tareaRepository.findAllWithLimitAndOffset(limit, offset);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de tareas", e);
        }
    }

    public List<Tarea> getAllTareasUser(int id){
        try {
            List<Tarea> tareas = tareaRepository.findAllByIdUser(id);
            if (tareas != null) {
                return tareas;
            } else {
                throw new RuntimeException("Tareas no encontradas");
            }
        } catch (Exception e){
            throw new RuntimeException("Error al obtener las tareas", e);
        }
    }

    public Tarea getTareaById(Long id) {
        try {
            Tarea tarea = tareaRepository.findById(id);
            if (tarea != null) {
                return tarea;
            } else {
                throw new RuntimeException("Tarea no encontrada");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tarea", e);
        }
    }

    public void createTarea(Tarea tarea) {
        try {
            tareaRepository.save(tarea);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la tarea", e);
        }
    }

    public void updateTarea(Tarea tarea) {
        try {
            getTareaById(tarea.getId());
            tareaRepository.save(tarea);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la tarea", e);
        }
    }

    public void deleteTarea(Long id) {
        try {
            getTareaById(id);
            tareaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }

    public long getTotalCount() {
        try {
            return tareaRepository.getTotalCount();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el total de tareas", e);
        }
    }
}
