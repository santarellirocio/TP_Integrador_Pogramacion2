/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.entities;

/**
 *
 * @author santa
 */
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
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    // Getters y Setters

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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
