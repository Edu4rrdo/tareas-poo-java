
/*
 [  REV  ] [              DESCRIPCIÓN              ] [ RESP ] [    FECHA    ]
 ++++++++++-----------------------------------------++++++++++--------------+
 |         | Se inicia el proyecto desde cero par- |          |             |
 | Inicial | a desarrollar una aplicación en Java  |  EDU     |  15-MAR-XXVI|
 |         | como una aplicación                   |          |             |
 ++++++++++-----------------------------------------++++++++++--------------+
 |         | Se trabajan los cuadros de texto, a-  |          |             |
 |         | poyados en los elementos de PANEL y   |          |             |
 |    D    | la habilitación de un cuadro de texto |  EDU     |  24-MAR-XXVI|
 |         | para comenzar con la captura de valo- |          |             |
 |         | res en una aplicación                 |          |             |
 ++++++++++-----------------------------------------++++++++++--------------+
 */
//seccion de importación de librerias 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
   private Scanner entrada = new Scanner(System.in);
   private ArrayList<Producto> productos = new ArrayList<>();
    
   
   // Atributos
    public void mostrarMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("| Bienvenido al sistema de ventas Ferreteria LAS  |");
        System.out.println("|                                                 |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| 1. ALTA                                         |");
        System.out.println("| 2. INVENTARIO                                   |");
        System.out.println("| 3. VENTA al público                             |");
        System.out.println("| 4. Salir                                        |");
        System.out.println("+-------------------------------------------------+");
       System.out.println();
        System.out.println("elige una opcion: ");
    }

    public int seleccionarOpcion() {
     
        return entrada.nextInt();
        
    } //termina clase seleccionarOpcion

    public Producto crearProductoDesdeTeclado(){
        System.out.print("Ingrese ID: ");
        int ID = entrada.nextInt();
        entrada.nextLine();
        System.out.print("ingrese nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Ingrese el precio: ");
        double precio = entrada.nextDouble();
        System.out.print("Ingresa el Stock: ");
        int stock = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Ingrese la categoria: ");
        String categoria = entrada.nextLine();
        //return new Producto(ID, stock, nombre, categoria, precio);
        Producto producto = new Producto(ID, nombre, precio, stock, categoria);
        productos.add(producto);
        guardarProductoEnArchivo(producto);
        return producto;
    }
    public void mostrarInventario(){
        if (productos.isEmpty()){
            System.out.println("El inventario esta vacio ");
        } else {
            System.out.println("-------- Inventario PRODUCTOS ---------");
            for(Producto p : productos){
                p.mostrarProducto();
            }
        }

    }//termina mostrar inventario 
   public void guardarProductoEnArchivo(Producto p){
        try {
            FileWriter writer = new FileWriter("productos.txt", true);
          String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();
            
            writer.write(linea + "\n"); // Escribe y da el salto de línea
            writer.close();             // Cierra el documento para guardar cambios
            System.out.println("Producto guardado en archivo");
            
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
            e.printStackTrace();
        }
    }
    }//Termina metodo guardarProductoEnArchivo

