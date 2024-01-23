package MsReservaHabitacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MsReservaHabitacion.dto.ReservaHabitacionDTO;
import MsReservaHabitacion.model.ReservaHabitacion;
import MsReservaHabitacion.services.ReservaHabitacionService;



@RestController
@RequestMapping("/reservas-habitacion")
public class ReservaHabitacionController {

    @Autowired
    private ReservaHabitacionService reservaHabitacionService;

    @GetMapping
    public ResponseEntity<Iterable<ReservaHabitacion>> getAllReservasHabitacion() {
        Iterable<ReservaHabitacion> reservasHabitacion = reservaHabitacionService.getAllReservasHabitacion();
        return ResponseEntity.ok(reservasHabitacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaHabitacion> getReservaHabitacionById(@PathVariable Long id) {
        ReservaHabitacion reservaHabitacion = reservaHabitacionService.getReservaHabitacionById(id);
        if (reservaHabitacion != null) {
            return ResponseEntity.ok(reservaHabitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReservaHabitacion> createReservaHabitacion(@RequestBody ReservaHabitacionDTO nuevaReserva) {
        ReservaHabitacion reservaCreada = reservaHabitacionService.createReservaHabitacion(nuevaReserva);
        return ResponseEntity.ok(reservaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaHabitacion> updateReservaHabitacion(@PathVariable Long id, @RequestBody ReservaHabitacionDTO reservaActualizada) {
        ReservaHabitacion reservaHabitacionActualizada = reservaHabitacionService.updateReservaHabitacion(id, reservaActualizada);
        if (reservaHabitacionActualizada != null) {
            return ResponseEntity.ok(reservaHabitacionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservaHabitacion(@PathVariable Long id) {
        reservaHabitacionService.deleteReservaHabitacion(id);
        return ResponseEntity.noContent().build();
    }
}
