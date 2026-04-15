/*
[ REV ] [               DESCRIPCIÓN              ] [ RESP ] [    FECHA    ]
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        | Se genera la clase main para iniciar |        |              |
|Inicial| la aplicacion                        | EDU    | 21-AGO-XXVI  |
        |                                      |        |              |
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
public class Main {
  public static void main(String[] args) {
    Menu menu = new Menu();
    int opcion;
    Producto producto1 = null;
    do {
    menu.mostrarMenu();
    opcion = menu.seleccionarOpcion();
    switch(opcion){
      case 1: 
      producto1 = menu.crearProductoDesdeTeclado();
      //producto1 = new Producto(10, 2, "Martillo", "herramientas", 65.50);
       System.out.println("ALTA PRODUCTO");
       break;
      case 2: System.out.println("INVENTARIO PRODUCTO");
      producto1.mostrarProducto();
      break;

        case 3:
           System.out.println("REALIZANDO VENTA");
           break;

         case 4: System.out.println("Saliendo...");
         break;
         
      default: 
      System.out.println("opcion no valida");
      break;
    }
    }while(opcion!=04);
  }//termina el psvm
}//termina la clase Main
