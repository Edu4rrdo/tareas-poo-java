public class Producto {
    // 1. Atributos con id en minúsculas como los tiene el profe
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    // 2. Constructor con el orden exacto del profesor
   public Producto(int id, String nombre, double precio, int stock, String categoria){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        System.out.println("El producto "+ nombre +" ha sido creado con el ID: "+ id);
    }//termina el metodo constructor
        
    

    public void mostrarProducto(){
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Categoría: " + categoria);
    }

    // 3. Getters actualizados para usar "id" en minúscula
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
}