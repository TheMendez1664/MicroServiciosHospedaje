package MsHabitacion.MsHabitacion.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MsHabitacion.MsHabitacion.dto.HabitacionDTO;
import MsHabitacion.MsHabitacion.model.Habitacion;
import MsHabitacion.MsHabitacion.repository.HabitacionRepository;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository, ModelMapper modelMapper) {
        this.habitacionRepository = habitacionRepository;
        this.modelMapper = modelMapper;
    }

    public List<HabitacionDTO> getAllHabitaciones() {
        try {
            Iterable<Habitacion> habitaciones = habitacionRepository.findAll();
            return convertToDTOList(habitaciones);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las habitaciones", e);
        }
    }

    public HabitacionDTO getHabitacionById(Long id) {
        try {
            Optional<Habitacion> habitacionOptional = habitacionRepository.findById(id);
            return habitacionOptional.map(this::convertToDTO).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la habitación", e);
        }
    }

    public HabitacionDTO createHabitacion(HabitacionDTO nuevaHabitacionDTO) {
        try {
            Habitacion nuevaHabitacion = convertToEntity(nuevaHabitacionDTO);
            Habitacion habitacionCreada = habitacionRepository.save(nuevaHabitacion);
            return convertToDTO(habitacionCreada);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la habitación", e);
        }
    }

    public void deleteHabitacion(Long id) {
        try {
            habitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la habitación", e);
        }
    }

    private List<HabitacionDTO> convertToDTOList(Iterable<Habitacion> habitaciones) {
        return StreamSupport.stream(habitaciones.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HabitacionDTO convertToDTO(Habitacion habitacion) {
        return modelMapper.map(habitacion, HabitacionDTO.class);
    }

    private Habitacion convertToEntity(HabitacionDTO habitacionDTO) {
        return modelMapper.map(habitacionDTO, Habitacion.class);
    }
}
