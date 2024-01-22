package MsReservaHabitacion.MsReservaHabitacion.repository;

import org.springframework.data.repository.CrudRepository;

import MsReservaHabitacion.MsReservaHabitacion.model.ReservaHabitacion;

public interface ReservaHabitacionRepository extends CrudRepository<ReservaHabitacion, Long> {
    // Puedes agregar métodos de consulta específicos si los necesitas
}
