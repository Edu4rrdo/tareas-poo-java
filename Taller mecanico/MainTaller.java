import java.util.Scanner;

public class MainTaller {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        MenuTaller menu = new MenuTaller();
        menu.cargarRefaccionesDesdeArchivo(); // Carga los datos al iniciar
        int opcion;

        do {
            menu.mostrarMenu(); // Muestra el panel de opciones
            opcion = menu.seleccionarOpcion(); // Captura la opción elegida
            
            switch(opcion){
                case 1: 
                    System.out.println("\n--- ALTA DE REFACCIÓN ---");
                    menu.crearRefaccionDesdeTeclado(); // Registra y guarda una nueva pieza
                    break;

                case 2:
                    // Muestra el inventario guardado en el archivo de texto
                    menu.mostrarInventarioDesdeArchivo(); 
                    break;

                case 3:
                    System.out.println("\n--- BUSCAR REFACCIÓN ---");
                    System.out.print("Ingrese el ID de la refacción a buscar: ");
                    int idBuscado = entrada.nextInt();
                    
                    // Usa el método de búsqueda lógica de tu MenuTaller
                    Refaccion refaccionEncontrada = menu.buscaRefaccionPorID(idBuscado); 
                    
                    if (refaccionEncontrada != null) {
                        System.out.println("\nRefacción Encontrada:");
                        refaccionEncontrada.mostrarRefaccion(); // Muestra sus detalles individuales
                    } else {
                        System.out.println("Error: Refacción no encontrada con el ID: " + idBuscado);
                    }
                    break;

               case 4:
                    System.out.println("\n--- ELIMINAR REFACCIÓN ---");
                    System.out.print("Ingresa el ID de la refacción a eliminar: ");
                    int idEliminar = entrada.nextInt();
                    
                    // Buscamos si existe la refacción
                    Refaccion refaccionEliminar = menu.buscaRefaccionPorID(idEliminar);
                    
                    if (refaccionEliminar == null) {
                        System.out.println("Error: Producto NO encontrado.");
                    } else {
                        System.out.println("\nProducto Encontrado:");
                        refaccionEliminar.mostrarRefaccion();
                        
                        System.out.print("¿Deseas Eliminar esta refacción del sistema? (s/n): ");
                        entrada.nextLine(); // Limpieza de buffer
                        String respuesta = entrada.nextLine();
                        
                        // Si el usuario confirma con 's', se borra de la lista y del archivo .txt
                        if (respuesta.equalsIgnoreCase("s")) {
                            menu.eliminarRefaccionDeLista(refaccionEliminar); // <--- AQUÍ SE HACE EL BORRADO REAL
                        } else {
                            System.out.println("Operación Cancelada.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- NUEVA ORDEN DE SERVICIO ---");
                    menu.crearOrdenServicio(); // Genera el presupuesto y descuenta stock
                    break;

                case 6:
                    // Llamamos a la función inventada
                    menu.mostrarReporteBajoStock();
                    break;

                case 7: 
                    System.out.println("Saliendo del sistema automotriz...");
                    break;

                default: 
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while(opcion != 7); // <-- IMPORTANTE: Cambiamos esto a 7 para que no se cierre con el 6
        
        entrada.close(); 
    }
}
