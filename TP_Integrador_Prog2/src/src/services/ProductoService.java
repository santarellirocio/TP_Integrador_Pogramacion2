
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.Producto;
import src.exceptions.EntidadNoEncontradaException;


public class ProductoService {
    // Lista para almacenar los productos en memoria
    private List<Producto> productos = new ArrayList<>();

    // Devuelve todos los productos disponibles
    public List<Producto> listar() {
        return productos;
    }
    
    // Agrega un producto nuevo a la lista
    public void crear(Producto p) {
        productos.add(p);
    }

    // Busca un producto por su ID; lanza excepción si no existe
    public Producto buscarPorId(Long id) throws EntidadNoEncontradaException {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Producto con ID " + id + " no encontrado."));
    }

    // Actualiza la información de un producto existente
    public void editar(Producto productoActualizado) throws EntidadNoEncontradaException {
        Producto p = buscarPorId(productoActualizado.getId());
        productos.remove(p);
        productos.add(productoActualizado);
    }

    // Elimina un producto de la lista
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Producto p = buscarPorId(id);
        productos.remove(p);
    }
}
