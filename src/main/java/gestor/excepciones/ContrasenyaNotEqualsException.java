package gestor.excepciones;

public class ContrasenyaNotEqualsException extends Exception{
    public ContrasenyaNotEqualsException() {
        super("Las contraseñas no coinciden.");
    }

    public ContrasenyaNotEqualsException(String mensaje){
        super(mensaje);
    }
}
