package MsHabitacion.MsHabitacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MsHabitacion.MsHabitacion.dto.HabitacionDTO;
import MsHabitacion.MsHabitacion.services.HabitacionService;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    /**
     * @return
     */
    @GetMapping("/habitaciones")
    public ResponseEntity<List<HabitacionDTO>> getAllHabitaciones() {
        List<HabitacionDTO> habitaciones = habitacionService.getAllHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }

    @GetMapping("/habitacion/{id}")
    public ResponseEntity<HabitacionDTO> getHabitacionById(@PathVariable Long id) {
        HabitacionDTO habitacion = habitacionService.getHabitacionById(id);
        return (habitacion != null) ? new ResponseEntity<>(habitacion, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/crear")
    public ResponseEntity<HabitacionDTO> createHabitacion(@RequestBody HabitacionDTO habitacionDTO) {
        HabitacionDTO nuevaHabitacion = habitacionService.createHabitacion(habitacionDTO);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    @DeleteMapping("/habitacion/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionService.deleteHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
