package MsHabitacion.MsHabitacion.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MsHabitacion.MsHabitacion.ApiRes.ApiResponse;
import MsHabitacion.MsHabitacion.dto.HabitacionDTO;
import MsHabitacion.MsHabitacion.services.HabitacionService;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/habitaciones")
    public ResponseEntity<ApiResponse<List<HabitacionDTO>>> getAllHabitaciones() {
        try {
            List<HabitacionDTO> habitaciones = habitacionService.getAllHabitaciones();
            return ResponseEntity.ok(ApiResponse.success(habitaciones));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Error al obtener las habitaciones"));
        }
    }

    @GetMapping("/habitacion/{id}")
    public ResponseEntity<ApiResponse<HabitacionDTO>> getHabitacionById(@PathVariable Long id) {
        try {
            HabitacionDTO habitacion = habitacionService.getHabitacionById(id);
            return (habitacion != null) ? ResponseEntity.ok(ApiResponse.success(habitacion))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Habitación no encontrada"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Error al obtener la habitación"));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<HabitacionDTO>> createHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        try {
            HabitacionDTO nuevaHabitacion = habitacionService.createHabitacion(habitacionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(nuevaHabitacion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Error al crear la habitación"));
        }
    }

    @DeleteMapping("/habitacion/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        try {
            habitacionService.deleteHabitacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
