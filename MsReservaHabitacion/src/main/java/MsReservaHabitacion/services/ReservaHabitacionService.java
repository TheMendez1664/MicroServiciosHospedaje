package MsReservaHabitacion.services;

import MsReservaHabitacion.dto.ReservaHabitacionDTO;
import MsReservaHabitacion.model.ReservaHabitacion;

public interface ReservaHabitacionService {

    Iterable<ReservaHabitacion> getAllReservasHabitacion();

    ReservaHabitacion getReservaHabitacionById(Long id);

    ReservaHabitacion createReservaHabitacion(ReservaHabitacionDTO nuevaReserva);

    ReservaHabitacion updateReservaHabitacion(Long id, ReservaHabitacionDTO reservaActualizada);

    void deleteReservaHabitacion(Long id);
}
