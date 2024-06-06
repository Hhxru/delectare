import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorEventos gestorEventos = new GestorEventos();
        Scanner sc = new Scanner(System.in);

        String opcion;

        do {
            System.out.println("""
                                        
                                        
                    ⋆｡ﾟ☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆ DELECTARE MULTIEVENTOS ⋆｡ﾟ☁︎｡⋆｡ ﾟ☾ ﾟ｡⋆
                    ꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷꒷꒦꒷꒦꒷꒦꒷꒦꒷꒦꒷
                                        
                       1.  ‧₊˚✧[Iniciar Sesión]✧˚₊‧                  
                       2.  ‧₊˚✧[Registro]✧˚₊‧                   
                       3.  ‧₊˚✧[Salir]✧˚₊‧             
 
                      ❀° ┄────────────────────────────────╮
                         Por favor, escoja una opción: ↓                                         
                      ╰────────────────────────────────┄ °❀                                                                                                              
                    """);

            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    gestorEventos.login();
                    break;
                case "2":
                    gestorEventos.registro();
                    break;
                case "3":
                    System.out.println("Hemos terminado.");
                    break;
                default:
                    System.out.println("Introduzca una opción válida.");
            }
        } while (!opcion.equals("3"));

    }
}
