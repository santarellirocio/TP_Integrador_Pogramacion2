/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.DetallePedido;
import src.entities.Pedido;
import src.entities.Producto;
import src.exceptions.EntidadNoEncontradaException;
import src.exceptions.StockInsuficienteException;

/**
 *
 * @author santa
 */
public class PedidoService {
    // Aquí es donde "vive" la base de datos en memoria
    private List<Pedido> pedidos = new ArrayList<>();

    public void guardar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Pedido p : pedidos) {
            // Validamos que no esté eliminado (baja lógica)
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        // Si el ciclo termina y no encontró nada, lanzamos la excepción
        throw new EntidadNoEncontradaException("Error: No se encontró un pedido activo con ID " + id);
    }

    public void eliminarLogico(Long id) throws EntidadNoEncontradaException {
        // 1. Buscamos el objeto. Si no existe, lanza la excepción que creamos
        Pedido p = buscarPorId(id);
        // 2. Marcamos el pedido como eliminado
        p.setEliminado(true);
        // 3. Marcamos los detalles como eliminados
        for (DetallePedido detalle : p.getDetalles()) {
            detalle.setEliminado(true);
        }
        System.out.println("Pedido #" + id + " eliminado correctamente.");
    }
    
    public List<Pedido> listarActivos() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }
    
    // Si ocurre una excepción al agregar un detalle, se captura el error y se cancela la creación
    public void agregarDetalle(Pedido pedido, Producto producto, int cantidad) throws StockInsuficienteException {
        //1. Validar regla de negocio antes de tocar nada
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("No hay stock suficiente para el producto: " + producto.getNombre());
        }
        // 2. Si pasó la validación, procedemos
        pedido.addDetallePedido(cantidad, producto);
        // 3. Descontar stock (importante para mantener coherencia en memoria)
        producto.setStock(producto.getStock() - cantidad);
    }
    
}
