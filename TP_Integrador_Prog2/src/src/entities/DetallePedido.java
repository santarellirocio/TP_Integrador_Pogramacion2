
package src.entities;

public class DetallePedido extends Base {

    // Contador privado exclusivo para esta clase
    private static Long contador = 100L;
    
    // Atributos
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    // Constructor
    public DetallePedido(int cantidad, Producto producto) {
        // Asignamos el ID autoincrementado automáticamente
        super(contador++); // La llamada a super() debe ser la primera instrucción del constructor.
        setCantidad(cantidad);
        setProducto(producto);
        this.subtotal = calcularSubtotal();
    }

    // Getters y Setters

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0){
            throw new IllegalArgumentException ("la cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
        throw new IllegalArgumentException("El producto no puede ser null.");
    }
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    // Metodo
    private Double calcularSubtotal() {
        // Validación de seguridad para prevenir NullPointerException
        if (this.producto == null) {
            return 0.0;
        }
        return cantidad * producto.getPrecio();
    }
    
    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Detalle del Pedido ID: " + getId() + 
            " | Producto: " + producto.getNombre() + 
            " | Cantidad: " + cantidad + 
            " | Subtotal: " + subtotal;
    }
    
}
