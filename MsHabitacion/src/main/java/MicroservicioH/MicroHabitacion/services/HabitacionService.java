package MsHabitacion.MsHabitacion.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MsHabitacion.MsHabitacion.dto.HabitacionDTO;
import MsHabitacion.MsHabitacion.model.Habitacion;
import MsHabitacion.MsHabitacion.repository.HabitacionRepository;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public List<HabitacionDTO> getAllHabitaciones() {
        Iterable<Habitacion> habitaciones = habitacionRepository.findAll();
        return convertToDTOList(habitaciones);
    }

    public HabitacionDTO getHabitacionById(Long id) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
        return (habitacion != null) ? convertToDTO(habitacion) : null;
    }

    public HabitacionDTO createHabitacion(HabitacionDTO nuevaHabitacionDTO) {
        Habitacion nuevaHabitacion = convertToEntity(nuevaHabitacionDTO);
        Habitacion habitacionCreada = habitacionRepository.save(nuevaHabitacion);
        return convertToDTO(habitacionCreada);
    }

    public void deleteHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }

    private List<HabitacionDTO> convertToDTOList(Iterable<Habitacion> habitaciones) {
        return StreamSupport.stream(habitaciones.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HabitacionDTO convertToDTO(Habitacion habitacion) {
        return new HabitacionDTO(
                habitacion.getNumeroHabitacion(),
                habitacion.getTipoHabitacion(),
                habitacion.getPrecio(),
                habitacion.isOcupada()
        );
    }

    private Habitacion convertToEntity(HabitacionDTO habitacionDTO) {
        Habitacion entity = new Habitacion();
        entity.setNumeroHabitacion(habitacionDTO.getNumeroHabitacion());
        entity.setTipoHabitacion(habitacionDTO.getTipoHabitacion());
        entity.setPrecio(habitacionDTO.getPrecio());
        entity.setOcupada(habitacionDTO.isOcupada());
        return entity;
    }
}
