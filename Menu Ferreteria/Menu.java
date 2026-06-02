
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner entrada = new Scanner(System.in);
    private ArrayList<Producto> productos = new ArrayList<>();
    
    public void mostrarMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("| Bienvenido al sistema de ventas Ferreteria LAS  |");
        System.out.println("|                                                 |");
        System.out.println("+-------------------------------------------------+");
        System.out.println("| 1. ALTA                                         |");
        System.out.println("| 2. INVENTARIO                                   |");
        System.out.println("| 3. Buscar producto por ID                       |");
        System.out.println("| 4. Borrar producto por ID                       |");
        System.out.println("| 5. VENTA al publico                             |");
        System.out.println("| 6. Salir                                        |");
        System.out.println("+-------------------------------------------------+");
        System.out.println();
        System.out.println("elige una opcion: ");
    }

    public int seleccionarOpcion() {
        return entrada.nextInt();
    }

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
    }

    public void guardarProductoEnArchivo(Producto p){
        try {
            FileWriter writer = new FileWriter("productos.txt", true);
            String linea = p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock() + "," + p.getCategoria();
            
            writer.write(linea + "\n");
            writer.close();
            System.out.println("Producto guardado en archivo");
            
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
            e.printStackTrace();
        }
    }

    // Método corregido: sin static, con mayúsculas correctas e imports agregados arriba
    public void mostrarInventarioDesdeArchivo(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
            String linea;
            System.out.println("\n Inventario del archivo ");
            while ((linea = reader.readLine()) != null){
                System.out.println(linea);
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("Error al leer el archivo ");
            e.printStackTrace();
        }
    } 
    public Producto buscaProductoPorID(int idBuscado){
        for (Producto p: productos){
            if (p.getId()==idBuscado){
                return p;
            }
        }
        return null; //producto no encontrado 

    }//termina método buscar producto por ID
       public void buscarProductoDesdeTeclado() {
        System.out.print("ingrese el ID del producto a buscar: ");
        int id = entrada.nextInt();

        Producto p = buscaProductoPorID(id);
        if (p != null){
            System.out.println("Producto Encontrado: ");
            p.mostrarProducto();
        } else {
            System.out.println("producto no encontrado ");
        }

       }//termina metodo de buscar producto desde el tecladoz

       public void eliminarProducto(){
     System.out.print("Ingresa ID del producto a eliminar");
     int id = entrada.nextInt();
     
     Producto productoEliminar = buscaProductoPorID(id);
     if (productoEliminar == null)
     {
         System.out.println("Producto NO encontrado");
         return;
     }
     System.out.println("Producto Encontrado");
     productoEliminar.mostrarProducto();
     System.out.println("¿Deseas Eliminar este producto? (s/n): ");
     entrada.nextLine(); //para limpiar el buffer
     String respuesta = entrada.nextLine();
     
     if (respuesta.equalsIgnoreCase("s")){
        productos.remove(productoEliminar);
        guardarTodosLosProductos();
        System.out.println("Producto eliminado correctamente");
     } else {
        System.out.println("Operación Cancelada");
     }
 }//Termina El Método De Eliminar Productos
         public void guardarTodosLosProductos(){
          try {
            FileWriter writer = new FileWriter("productos.txt"); 
            for (Producto p : productos){
               String linea = p.getId() + "," + 
                              p.getNombre() + "," +
                              p.getPrecio() + "," +
                              p.getStock() + "," +
                              p.getCategoria();
               writer.write(linea + "\n");
            }
            writer.close();
             System.out.println("Archivo actualizado correctamente");
          } catch (IOException e) {
               System.out.println("Error al actualizar el archivo");
          }
         }//termina guardar todos los productos
         public void cargarProductosDesdeArchivo() {
    productos.clear();
    try {
        // Se corrigen las mayúsculas de BufferedReader y FileReader
        BufferedReader reader = new BufferedReader(new FileReader("productos.txt"));
        String linea;
        while ((linea = reader.readLine()) != null) {
            // Se cambian los paréntesis por corchetes en el arreglo
            String[] partes = linea.split(","); 
            
            // Se cambian los paréntesis por corchetes al acceder a los índices
            int id = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            double precio = Double.parseDouble(partes[2]);
            int stock = Integer.parseInt(partes[3]);
            String categoria = partes[4];

            // Se cambia "producto" por "Producto" respetando la mayúscula
            Producto p = new Producto(id, nombre, precio, stock, categoria);
            
            // Se elimina el carácter extra al final
            productos.add(p); 
        }
        reader.close(); // Es una buena práctica cerrar tu lector
        
    } catch (Exception e) {
        System.out.println("Error al cargar los productos");
    }
}
public void crearFactura(){
        // 1. AQUI SE CREA EL OBJETO FACTURA QUE FALTABA
        Factura factura = new Factura(); 
        String continuar;
        
        do{
            System.out.println("Ingrese el ID del producto a vender: ");
            int id = entrada.nextInt();
            Producto producto = buscaProductoPorID(id);
            
            if(producto == null){
                System.out.println("Producto no encontrado");
            } else {
                producto.mostrarProducto();
                System.out.println("cantidad a vender: ");
                int cantidad = entrada.nextInt();
                
                DetalleFactura detalle = new DetalleFactura(producto, cantidad);
                // 2. AQUI SE USA 'factura' (el objeto en minúscula), NO 'Factura' (la clase)
                factura.agregarDetalle(detalle); 
                System.out.println("producto agregado a factura");
            }
            
            entrada.nextLine(); //limpiar buffer
            System.out.println("¿Desea agregar otro producto? (s/n): ");
            continuar = entrada.nextLine();
            
        } while (continuar.equalsIgnoreCase("s"));
        
        // 3. Ahora esta línea ya no dará error porque 'factura' existe
        factura.mostrarFactura(); 
    }

    
}//termina CREAR FACTURA 
            
         

    
