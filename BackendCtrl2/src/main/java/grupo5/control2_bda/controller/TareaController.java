package grupo5.control2_bda.controller;

import grupo5.control2_bda.models.Tarea;
import grupo5.control2_bda.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTareas(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            List<Tarea> tareas = tareaService.getAllTareas(limit, offset);
            long totalCount = tareaService.getTotalCount();
            Map<String, Object> response = new HashMap<>();
            response.put("tareas", tareas);
            response.put("totalCount", totalCount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAllUser/{id}")
    public ResponseEntity<List<Tarea>> getAllTareasUser(@PathVariable int id){
        try{
            List<Tarea> Tareas = tareaService.getAllTareasUser(id);
            return  ResponseEntity.ok(Tareas);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        try {
            Tarea tarea = tareaService.getTareaById(id);
            return ResponseEntity.ok(tarea);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getExpiringById/{id}")
    public ResponseEntity<List<Tarea>> getExpiringTareasByIdUser(@PathVariable int id) {
        try{
            List<Tarea> Tareas = tareaService.getExpiringTareasByIdUser(id);
            return ResponseEntity.ok(Tareas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createTarea(@RequestBody Tarea tarea) {
        try {
            tareaService.createTarea(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tarea creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la tarea");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            tarea.setId(id);
            tareaService.updateTarea(tarea);
            return ResponseEntity.ok("Tarea actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la tarea");
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<String> completeTarea(@PathVariable Long id) {
        try {
            tareaService.completeTarea(id);
            return ResponseEntity.ok("Tarea completada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al completar la tarea");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
        try {
            tareaService.deleteTarea(id);
            return ResponseEntity.ok("Tarea eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar la tarea");
        }
    }
}
