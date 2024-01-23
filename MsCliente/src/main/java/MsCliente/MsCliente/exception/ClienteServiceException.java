package MsCliente.MsCliente.exception;

public class ClienteServiceException extends RuntimeException {

    public ClienteServiceException(String message) {
        super(message);
    }

    public ClienteServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
