
package src.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import src.enums.Estado;
import src.enums.FormaPago;
import src.exceptions.StockInsuficienteException;
import src.interfaces.Calculable;


public class Pedido extends Base implements Calculable {

    // Atributos
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private List<DetallePedido> detalles;//composicion - relacion unidireccional 1:N - un pedido contiene muchos detalles de pedido
    private Usuario usuario;

    // Constructor
    public Pedido(Long id, Estado estado, FormaPago formaPago, Usuario usuario) {
        super(id); // La llamada a super() debe ser la primera instrucción del constructor.
        this.fecha = LocalDate.now();
        setEstado(estado);
        setFormaPago(formaPago);
        setUsuario(usuario);
        this.detalles = new ArrayList<>();
    }

    // Getters
    public Double getTotal() {
        return total;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }
    
    //setters

    public void setEstado(Estado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("Estado inválido");
        }
        this.estado = estado;
    }

    public void setFormaPago(FormaPago formaPago) {
         if (formaPago == null){
            throw new IllegalArgumentException("forma de pago invalida");
        }
        this.formaPago = formaPago;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    // Sobrescritura del método de la interface
    // Calcula el total sumando los subtotales de cada detalle
    @Override
    public void calcularTotal() {
        total = 0.0;
        for (DetallePedido detalle : detalles) {
            total += detalle.getSubtotal();
        }
    }

    // Metodos
    
    public void addDetallePedido(int cantidad, Producto producto) throws StockInsuficienteException{
        // 1. PRIMERO: Validamos la regla de negocio
        // Si no hay suficiente stock, lanzamos la excepción y el método se detiene aquí.
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre() + 
                                                ". Disponible: " + producto.getStock());
        }
        // 2. DESPUÉS: Si pasamos la validación, procedemos con la lógica
        // Creamos el objeto, el ID se genera solo dentro del constructor de DetallePedido
        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        detalles.add(nuevoDetalle);
        // 3. ACTUALIZAMOS: Recalculamos el total del pedido
        calcularTotal();
        // 4. Restamos el stock del producto
        producto.setStock(producto.getStock() - cantidad);
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido aEliminar = findDetallePedidoByProducto(producto);
        if (aEliminar != null) {
            this.detalles.remove(aEliminar);
            calcularTotal();
        }
    }

    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Pedido ID: " + getId() + 
            " | Fecha: " + fecha + 
            " | Estado: " + estado + 
            " | Forma de pago: " + formaPago +
            " | Total: $" + total;        
    }
    
}
