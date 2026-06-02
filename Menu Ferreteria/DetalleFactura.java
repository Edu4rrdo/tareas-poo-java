public class DetalleFactura {
    private Producto producto;
    private int cantidad; 
    private double subtotal;
    public DetalleFactura(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }//termina el metodo constructor
    public void calcularSubtotal(){
        subtotal = producto.getPrecio() * cantidad;
    }//termina el metodo calcular subtotal
    public void mostrarDetalle(){
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("precio: $" + producto.getPrecio());
        System.out.println("Subtotal: $" + subtotal);
    }//termina el metodo mostrarDetalle
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}//Termina la clase DetalleFactura
