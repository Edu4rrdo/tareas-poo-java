public class Refaccion {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String marca; // Cambiamos categoría por marca/vehículo

    public Refaccion(int id, String nombre, double precio, int stock, String marca){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        System.out.println("La refacción "+ nombre +" ha sido registrada con el ID: "+ id);
    }

    public void mostrarRefaccion(){
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Marca/Aplicación: " + marca);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getMarca() { return marca; }
    
    // Método para descontar stock cuando se hace un servicio
    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
}