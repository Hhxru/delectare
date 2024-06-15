package gestor;

import java.time.DateTimeException;
import java.time.LocalDate;
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
    public static LocalDate validarFecha(String fecha){
        LocalDate mifecha;
        if(fecha.length() != 10){
            System.out.println("Longitud Invalida.");
            return null;
        }
        String[] partesFecha = fecha.split("-");
        if (partesFecha.length != 3){
            System.out.println("Formato fecha invalido.");
            return null;

        }
         try{
             System.out.println(partesFecha[0]);
             int anyo=Integer.parseInt(partesFecha[0]);
             int mes=Integer.parseInt(partesFecha[1]);
             int dia=Integer.parseInt(partesFecha[2]);
             mifecha=LocalDate.of(anyo, mes, dia);
             return mifecha;
         }catch (NumberFormatException e){
             System.err.println(e.getMessage());
         }catch (DateTimeException d){
             System.out.println(d.getMessage());
         }
        return null;
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

    // Valida el DNI verificando que tenga 8 números y una letra al final
    public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            char digito = dni.charAt(i);
            if (digito < '0' || digito > '9') {
                return false;
            }
        }
        char calcularLetra = calcularLetraDni(dni.substring(0, 8)); // Calcular la letra a partir de los 8 dígitos
        char letraDNI = Character.toUpperCase(dni.charAt(8));

        return letraDNI == calcularLetra;
    }

    // Calcula la letra sacando el resto de dividir la cadena de números entre 23, y busca su posición en el String empezando por 0
    private static char calcularLetraDni(String numeros) {
        int numeroDNI = Integer.parseInt(numeros);
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int indice = numeroDNI % 23;
        return letras.charAt(indice);
    }

    // Token con UUID
    public static String generarUUID() {
        //generar UUID aleatoria
        UUID uuid = UUID.randomUUID();
        System.out.println("UUID generada: " + uuid.toString());
        return uuid.toString();
    }
}
