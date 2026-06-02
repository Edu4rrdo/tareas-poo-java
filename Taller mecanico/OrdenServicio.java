import java.util.ArrayList;

public class OrdenServicio {
    private String cliente;
    private String placasVehiculo;
    private ArrayList<DetalleOrden> detalles;
    private double manoDeObra;
    private double total;

    public OrdenServicio(String cliente, String placasVehiculo, double manoDeObra){
        this.cliente = cliente;
        this.placasVehiculo = placasVehiculo;
        this.manoDeObra = manoDeObra;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }
    
    public void agregarDetalle(DetalleOrden detalle){
        detalles.add(detalle);
        calcularTotal();
    }

    public void calcularTotal(){ 
        total = manoDeObra; // El total inicia con el costo de mano de obra
        for(DetalleOrden d : detalles){
            total += d.getSubtotal();
        }
    }

    public void mostrarOrden(){
        System.out.println("\n======== ORDEN DE SERVICIO ========");
        System.out.println("Cliente: " + cliente);
        System.out.println("Vehículo (Placas): " + placasVehiculo);
        System.out.println("Costo Mano de Obra: $" + manoDeObra);
        System.out.println("--- Refacciones Utilizadas ---");
        for(DetalleOrden d : detalles){
            d.mostrarDetalle();
            System.out.println("---------------------------------");    
        }
        System.out.println("TOTAL A PAGAR: $" + total);
        System.out.println("===================================");
    }
}