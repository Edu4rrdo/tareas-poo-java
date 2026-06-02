public class MainTaller {
    public static void main(String[] args) {
        MenuTaller menu = new MenuTaller();
        menu.cargarRefaccionesDesdeArchivo();
        int opcion;

        do {
            menu.mostrarMenu();
            opcion = menu.seleccionarOpcion();
            
            switch(opcion){
                case 1: 
                    System.out.println("\n--- ALTA DE REFACCIÓN ---");
                    menu.crearRefaccionDesdeTeclado();
                    break;
                case 2:
                    menu.mostrarInventarioDesdeArchivo();
                    break;
                case 3:
                    System.out.println("\n--- BUSCAR REFACCIÓN ---");
                    System.out.print("Ingrese el ID a buscar: ");
                    // Aquí puedes implementar el input directo y llamar a buscaRefaccionPorID
                    break;
                case 4:
                    System.out.println("\n--- ELIMINAR REFACCIÓN ---");
                    // Implementación similar a eliminarProducto de tu código original
                    break;
                case 5:
                    System.out.println("\n--- NUEVA ORDEN DE SERVICIO ---");
                    menu.crearOrdenServicio(); 
                    break;
                case 6: 
                    System.out.println("Saliendo del sistema...");
                    break;
                default: 
                    System.out.println("Opción no válida.");
                    break;
            }
        } while(opcion != 6);
    }
}