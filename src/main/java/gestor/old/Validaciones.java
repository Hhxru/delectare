package gestor.old;

public class Validaciones {

    public static class DNIException extends Exception {
        public DNIException() {
            super("El DNI introducido es incorrecto.");
        }

        public DNIException(String message) {
            super(message);
        }
    }

    public static boolean isNum(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (!(num.charAt(i) >= '0' && num.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlp(String alp) {
        String permitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < alp.length(); i++) {
            char caracter = alp.charAt(i);
            if (permitidos.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlpEsp(String alpEsp) {
        String permitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789áéíóúÁÉÍÓÚñÑüÜçÇ";
        for (int i = 0; i < alpEsp.length(); i++) {
            char caracter = alpEsp.charAt(i);
            if (permitidos.indexOf(caracter) == -1) {
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

    public static void validarPassLog(String pass) {
        do {
            if (pass.length() >= 8) {
                break;
            } else {
                break;
            }
        } while (pass.length() < 8);
    }

    public static void validarPass(String pass) {
        do {
            if (pass.length() >= 8) {
                System.out.println("Contraseña valida.");
                break;
            } else {
                System.err.println("Error, la contraseña es insegura.");
                break;
            }
        } while (pass.length() < 8);
    }

    public static boolean validarNombre(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            if (!((caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z') || caracter == ' ')) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarApellido(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            if (!((caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z') || caracter == ' ')) {
                return false;
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

    // Valida el IBAN comprobando si empieza por dos letras mayúsculas y tiene 24 caracteres en total
    public static boolean validarIBAN(String iban) {
        if (iban.length() != 24) {
            return false;
        }

        for (int i = 0; i < 2; i++) {
            char letra = iban.charAt(i);

            if (!(letra >= 'A' && letra <= 'Z')) {
                return false;
            }
        }

        for (int i = 2; i < 24; i++) {
            char caracter = iban.charAt(i);

            if (!(caracter >= '0' && caracter <= '9')) {
                return false;
            }
        }
        return true;
    }
}
