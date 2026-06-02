import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTaller {
    private Scanner entrada = new Scanner(System.in);
    private ArrayList<Refaccion> inventario = new ArrayList<>();
    
    public void mostrarMenu() {
        System.out.println("\n+-------------------------------------------------+");
        System.out.println("|      SISTEMA DE GESTIÓN AUTOMOTRIZ (2026)       |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| 1. Alta de Refacción                            |");
        System.out.println("| 2. Ver Inventario de Almacén                    |");
        System.out.println("| 3. Buscar Refacción por ID                      |");
        System.out.println("| 4. Eliminar Refacción                           |");
        System.out.println("| 5. Generar Orden de Servicio (Reparación)       |");
        System.out.println("| 6. Reporte de Bajo Stock (¡Nuevo!)              |");
        System.out.println("| 7. Salir                                        |");
        System.out.println("+-------------------------------------------------+");
        System.out.print("Elige una opción: ");
    }

   public int seleccionarOpcion() {
        try {
            return entrada.nextInt();
        } catch (InputMismatchException e) {
            entrada.nextLine(); // Limpiamos el buffer para que no se trabe leyendo la misma letra
            return -1; // Retornamos un número que no existe en el menú (-1)
        }
    }

    public void crearRefaccionDesdeTeclado(){
        System.out.print("Ingrese ID de refacción: ");
        int id = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Ingrese nombre (ej. Filtro de aceite): ");
        String nombre = entrada.nextLine();
        System.out.print("Ingrese el precio: ");
        double precio = entrada.nextDouble();
        System.out.print("Ingresa el Stock actual: ");
        int stock = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Ingrese la marca o compatibilidad: ");
        String marca = entrada.nextLine();
        
        Refaccion refaccion = new Refaccion(id, nombre, precio, stock, marca);
        inventario.add(refaccion);
        guardarTodosLosProductos(); // Actualiza el archivo
    }

    public void mostrarInventarioDesdeArchivo(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("refacciones.txt"));
            String linea;
            System.out.println("\n--- Inventario Actual ---");
            while ((linea = reader.readLine()) != null){
                String[] partes = linea.split(",");
                System.out.println("ID: " + partes[0] + " | " + partes[1] + " | Stock: " + partes[3] + " | $" + partes[2]);
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Error al leer el archivo o el inventario está vacío.");
        }
    } 

    public Refaccion buscaRefaccionPorID(int idBuscado){
        for (Refaccion r: inventario){
            if (r.getId() == idBuscado){
                return r;
            }
        }
        return null;
    }

    public void guardarTodosLosProductos(){
        try {
            FileWriter writer = new FileWriter("refacciones.txt"); 
            for (Refaccion r : inventario){
                String linea = r.getId() + "," + r.getNombre() + "," + r.getPrecio() + "," + r.getStock() + "," + r.getMarca();
                writer.write(linea + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo");
        }
    }

    public void cargarRefaccionesDesdeArchivo() {
        inventario.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("refacciones.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(","); 
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                double precio = Double.parseDouble(partes[2]);
                int stock = Integer.parseInt(partes[3]);
                String marca = partes[4];

                Refaccion r = new Refaccion(id, nombre, precio, stock, marca);
                inventario.add(r); 
            }
            reader.close();
        } catch (Exception e) {
            // Silencioso si es la primera vez y no hay archivo
        }
    }

    public void crearOrdenServicio(){
        entrada.nextLine(); // limpiar buffer
        System.out.print("Nombre del Cliente: ");
        String cliente = entrada.nextLine();
        System.out.print("Placas del Vehículo: ");
        String placas = entrada.nextLine();
        System.out.print("Costo estimado de Mano de Obra: $");
        double manoDeObra = entrada.nextDouble();
        
        OrdenServicio orden = new OrdenServicio(cliente, placas, manoDeObra); 
        String continuar;
        
        do {
            System.out.print("Ingrese el ID de la refacción a utilizar (0 si ninguna): ");
            int id = entrada.nextInt();
            
            if (id != 0) {
                Refaccion refaccion = buscaRefaccionPorID(id);
                
                if(refaccion == null){
                    System.out.println("Refacción no encontrada.");
                } else if (refaccion.getStock() <= 0) {
                    System.out.println("¡Alerta! No hay stock disponible para esta refacción.");
                } else {
                    System.out.print("Cantidad a utilizar: ");
                    int cantidad = entrada.nextInt();
                    
                    if (cantidad > refaccion.getStock()) {
                        System.out.println("Solo hay " + refaccion.getStock() + " en stock.");
                    } else {
                        DetalleOrden detalle = new DetalleOrden(refaccion, cantidad);
                        orden.agregarDetalle(detalle); 
                        refaccion.reducirStock(cantidad); // Descuenta del inventario lógico
                        guardarTodosLosProductos(); // Guarda los cambios en el archivo .txt
                        System.out.println("Refacción agregada a la orden.");
                    }
                }
            }
            
            entrada.nextLine(); // limpiar buffer
            System.out.print("¿Desea agregar otra refacción? (s/n): ");
            continuar = entrada.nextLine();
            
        } while (continuar.equalsIgnoreCase("s"));
        
        orden.mostrarOrden(); 
    }
    public void eliminarRefaccionDeLista(Refaccion r) {
    if (r != null) {
        inventario.remove(r); // Lo quita de la lista en memoria
        guardarTodosLosProductos(); // Sobreescribe el archivo refacciones.txt con la lista actualizada
        System.out.println("Refacción eliminada correctamente del archivo.");
    }
}
public void mostrarReporteBajoStock() {
        System.out.println("\n--- ALERTA: REPORTE DE BAJO STOCK ---");
        boolean hayBajoStock = false;
        
        for (Refaccion r : inventario) {
            // Si el stock es menor a 5, lanza la alerta
            if (r.getStock() < 5) {
                System.out.println("¡PRECAUCIÓN! " + r.getNombre() + " (ID: " + r.getId() + ") - Quedan solo: " + r.getStock() + " piezas.");
                hayBajoStock = true;
            }
        }
        
        if (!hayBajoStock) {
            System.out.println("Todo en orden. No hay refacciones con escasez de stock.");
        }
        System.out.println("-------------------------------------");
    }
}