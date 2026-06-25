
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.DetallePedido;
import src.entities.Pedido;
import src.exceptions.EntidadNoEncontradaException;


public class PedidoService {
    // Aquí es donde "vive" la base de datos en memoria
    private List<Pedido> pedidos = new ArrayList<>();
    
    // Devuelve todos los pedidos disponibles
    public List<Pedido> listar() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) activos.add(p);
        }
        return activos;
    }

    // Agrega un pedido nuevo a la lista
    public void crear(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Actualiza la información de un pedido existente
    public void editar(Pedido pedidoActualizado) throws EntidadNoEncontradaException {
        Pedido p = buscarPorId(pedidoActualizado.getId());
        int index = pedidos.indexOf(p);
        pedidos.set(index, pedidoActualizado);
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

    // Elimina un pedido de la lista
    public void eliminar(Long id) throws EntidadNoEncontradaException {
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
    
}
