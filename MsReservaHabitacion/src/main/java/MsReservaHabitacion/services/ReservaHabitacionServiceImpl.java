package MsReservaHabitacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MsReservaHabitacion.dto.ReservaHabitacionDTO;
import MsReservaHabitacion.model.ReservaHabitacion;
import MsReservaHabitacion.repository.ReservaHabitacionRepository;

@Service
public class ReservaHabitacionServiceImpl implements ReservaHabitacionService {

    @Autowired
    private ReservaHabitacionRepository reservaHabitacionRepository;

    @Override
    public Iterable<ReservaHabitacion> getAllReservasHabitacion() {
        return reservaHabitacionRepository.findAll();
    }

    @Override
    public ReservaHabitacion getReservaHabitacionById(Long id) {
        return reservaHabitacionRepository.findById(id).orElse(null);
    }

    @Override
    public ReservaHabitacion createReservaHabitacion(ReservaHabitacionDTO nuevaReserva) {
        ReservaHabitacion reservaHabitacion = new ReservaHabitacion(
                nuevaReserva.getIdCliente(),
                nuevaReserva.getIdHabitacion(),
                nuevaReserva.getFechaInicio(),
                nuevaReserva.getFechaFin()
        );
        return reservaHabitacionRepository.save(reservaHabitacion);
    }

    @Override
    public ReservaHabitacion updateReservaHabitacion(Long id, ReservaHabitacionDTO reservaActualizada) {
        ReservaHabitacion reservaExistente = reservaHabitacionRepository.findById(id).orElse(null);

        if (reservaExistente != null) {
            reservaExistente.setIdCliente(reservaActualizada.getIdCliente());
            reservaExistente.setIdHabitacion(reservaActualizada.getIdHabitacion());
            reservaExistente.setFechaInicio(reservaActualizada.getFechaInicio());
            reservaExistente.setFechaFin(reservaActualizada.getFechaFin());

            return reservaHabitacionRepository.save(reservaExistente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReservaHabitacion(Long id) {
        reservaHabitacionRepository.deleteById(id);
    }
}
