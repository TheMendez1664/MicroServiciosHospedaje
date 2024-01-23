package MsHospedaje.MsUsuario.dto;

public class UsuarioDTO {

    private int idUsuario;
    private String usuario;
    private String clave;

    public UsuarioDTO() {
        // Constructor vacío necesario para algunas operaciones de mapeo
    }

    public UsuarioDTO(int idUsuario, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // Puedes agregar métodos adicionales si es necesario
}
