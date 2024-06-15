package gestor.excepciones;

public class EmailExistenteException extends Exception{
    public EmailExistenteException() {
        super("El correo electronico esta en uso.");
    }

    public EmailExistenteException(String mensaje){
        super(mensaje);
    }
}
