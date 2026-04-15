public class Producto {
    private int ID, stock;
    private String nombre, categoria;
    private double precio;
    public Producto(int ID, int stock, String nombre, String categoria, double precio){
        this.ID = ID;
        this.stock = stock;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        System.out.println("El producto "+ nombre +" ha sido creado con el ID: "+ ID);
    }//termina el metodo constructor
    public void mostrarProducto(){
        System.out.println("ID: "+ ID);
        System.out.println("Nombre: "+ nombre   );
        System.out.println("Precio: "+ precio);
        System.out.println("Stock: "+ stock);
        System.out.println("Categoria: "+ categoria);

    }

}
