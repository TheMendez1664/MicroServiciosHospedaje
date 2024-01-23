package MsReservaHabitacion.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class ReservaHabitacionDTO implements Serializable {

    private Long idCliente;
    private Long idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructor vacío
    public ReservaHabitacionDTO() {
    }

    // Constructor con parámetros
    public ReservaHabitacionDTO(Long idCliente, Long idHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Otros getters y setters según necesidad
}
