public class DetalleOrden {
    private Refaccion refaccion;
    private int cantidad; 
    private double subtotal;

    public DetalleOrden(Refaccion refaccion, int cantidad){
        this.refaccion = refaccion;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public void calcularSubtotal(){
        subtotal = refaccion.getPrecio() * cantidad;
    }

    public void mostrarDetalle(){
        System.out.println("Refacción: " + refaccion.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio unitario: $" + refaccion.getPrecio());
        System.out.println("Subtotal: $" + subtotal);
    }

    public Refaccion getRefaccion() { return refaccion; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}