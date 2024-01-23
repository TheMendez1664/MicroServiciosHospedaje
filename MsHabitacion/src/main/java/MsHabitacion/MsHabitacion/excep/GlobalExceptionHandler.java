package MsHabitacion.MsHabitacion.excep;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        // Puedes personalizar el mensaje de error según tus necesidades
        return new ResponseEntity<>("Error en la aplicación: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
