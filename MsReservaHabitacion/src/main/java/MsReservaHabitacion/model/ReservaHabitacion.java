package MsReservaHabitacion.model;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Document(collection = "ReservaHabitacion") // Nombre de la colección en MongoDB
public class ReservaHabitacion {

    @Id
    private String id;

    private Long idCliente;
    private Long idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Constructores, getters y setters

    // Constructor vacío
    public ReservaHabitacion() {
    }

    // Constructor con parámetros
    public ReservaHabitacion(Long idCliente, Long idHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    // Método toString
    @Override
    public String toString() {
        return "ReservaHabitacion{" +
                "id='" + id + '\'' +
                ", idCliente=" + idCliente +
                ", idHabitacion=" + idHabitacion +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
