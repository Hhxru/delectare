import java.util.UUID;

public class Validaciones {

    // Cadena con todos los caracteres permitidos del alfabeto latino
    private static final String PERMITIDOS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZáéíóúÁÉÍÓÚñÑüÜçÇ";

    //Metodo para validar que un nombre solo contenga caracteres de la cadena permitidos
    public static boolean validarNombre(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            // si indexof da -1 (no encuentra el caracter) o este no es un espacio en blanco, devuelve false
            if (PERMITIDOS.indexOf(caracter) == -1 && caracter != ' ') {
                return false;
            }
        }
        return true;
    }

    // Valida el gmail verificando que existe un caracter antes de @, entre @ y . y mínimo uno después del .
    public static boolean validarGmail(String email) {
        int arrobaIndice = email.indexOf('@');
        int puntoIndice = email.lastIndexOf('.');

        if (arrobaIndice > 0 && puntoIndice > arrobaIndice + 1 && puntoIndice < email.length() - 1) {
            return true;
        } else {
            return false;
        }
    }

    // Valida el número de teléfono
    public static boolean validarNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono.length() != 11) { // queremos que sean 11 para que al incluir 2 espacios, se queden como 9 números
            return false;
        }
        for (int i = 0; i < 11; i++) {
            char posicionActual = numeroTelefono.charAt(i);

            if ((i == 3 || i == 7) && posicionActual != ' ') {
                return false;
            } else if (i != 3 && i != 7) {
                if (posicionActual < '0' || posicionActual > '9') {
                    return false;
                }
            }
        }
        return true;
    }

    // Token con UUID
    public static String generarUUID() {
        //generar UUID aleatoria
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID generada: " + uuid);
        return uuid.toString();
    }
}
