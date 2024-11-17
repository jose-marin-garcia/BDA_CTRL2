package grupo5.control2_bda.repository;

import grupo5.control2_bda.models.Tarea;

import java.util.List;

public interface TareaRepository {

    public List<Tarea> findAllWithLimitAndOffset(int limit, int offset);

    public List<Tarea> findAllByIdUser(int id);

    public Tarea findById(Long id);

    public void save(Tarea tarea);

    public void updateTarea(Tarea tarea);

    public void deleteById(Long id);

    public long getTotalCount();
}
