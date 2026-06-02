import java.util.ArrayList;

public class Factura {
    private ArrayList<DetalleFactura> detalles;
    private double total;
    public Factura(){
        detalles = new ArrayList<>();
        total = 0.0;
    }//termina el metodo constructor
    
    public void agregarDetalle(DetalleFactura detalle){
        detalles.add(detalle);
        calcularTotal();
    }//termina el metodo agregarDetalle
    public void calcularTotal(){ 
        total = 0.0;
        for(DetalleFactura d : detalles){
            total += d.getSubtotal();
        }
    }//termina el metodo calcularTotal
    public void mostrarFactura(){
        System.out.println("-------- Detalles de la Factura ---------");
        for(DetalleFactura d : detalles){
            d.mostrarDetalle();
            System.out.println("-----------------------------------------");    
            System.out.println("Total a pagar: $" + total);
        }

    }//termina el metodo calcularTotal
    public double getTotal() {
        return total;
    }
    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }
}
