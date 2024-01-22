package MsHabitacion.MsHabitacion.dto;


import java.io.Serializable;

public class HabitacionDTO implements Serializable {

    private int numeroHabitacion;
    private String tipoHabitacion;
    private double precio;
    private boolean ocupada;

    // Constructores, getters y setters

    public HabitacionDTO() {
    }

    public HabitacionDTO(int numeroHabitacion, String tipoHabitacion, double precio, boolean ocupada) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
        this.ocupada = ocupada;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
