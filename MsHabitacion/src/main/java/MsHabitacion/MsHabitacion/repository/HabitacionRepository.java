package MsHabitacion.MsHabitacion.repository;




import org.springframework.data.repository.CrudRepository;

import MsHabitacion.MsHabitacion.model.Habitacion;




public interface HabitacionRepository extends CrudRepository<Habitacion, Long> {
    // Puedes agregar métodos de consulta específicos si los necesitas
}
