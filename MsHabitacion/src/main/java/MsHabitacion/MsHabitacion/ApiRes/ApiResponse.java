package MsHabitacion.MsHabitacion.ApiRes;

public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;

    // Constructor vacío necesario para la deserialización JSON
    public ApiResponse() {
    }

    // Getter y Setter para el campo 'status'
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // Getter y Setter para el campo 'message'
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter y Setter para el campo 'data'
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Métodos adicionales según sea necesario

    // Método toString() para facilitar la depuración y registro
    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    // Método estático para crear una respuesta exitosa
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(200); // Código HTTP para operación exitosa
        response.setMessage("Operación exitosa");
        response.setData(data);
        return response;
    }

    // Método estático para crear una respuesta de error
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(500); // Código HTTP para error interno del servidor
        response.setMessage(message);
        return response;
    }
}
