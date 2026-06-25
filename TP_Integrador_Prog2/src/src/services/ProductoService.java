
package src.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import src.entities.Categoria;
import src.entities.Producto;
import src.exceptions.EntidadNoEncontradaException;


public class ProductoService {
    // Lista para almacenar los productos en memoria
    private List<Producto> productos = new ArrayList<>();
    private CategoriaService categoriaService;
    
    
    public ProductoService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    // Devuelve todos los productos disponibles
    public List<Producto> listar() {
        return Collections.unmodifiableList(productos);
    }    
    
    //sobrecarga de metodo crear
    public void crear(Producto producto) {
        if (producto == null) {
             throw new IllegalArgumentException("Producto nulo");
        }
        productos.add(producto);
    }
           
    // Agrega un producto nuevo a la lista
    public void crear(String nombre,Double precio,int stock,Long categoriaId)throws EntidadNoEncontradaException {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        Categoria categoria =categoriaService.buscarPorId(categoriaId);
        Long nuevoId = productos.stream().mapToLong(Producto::getId).max().orElse(0L) + 1;
        Producto producto = new Producto(nuevoId,nombre,precio,"",stock,"", true,categoria);
        productos.add(producto);
    }

    // Busca un producto por su ID; lanza excepción si no existe
    public Producto buscarPorId(Long id) throws EntidadNoEncontradaException {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Producto con ID " + id + " no encontrado."));
    }

    // Actualiza la información de un producto existente
    public void editar(Long id, String nuevoNombre)throws EntidadNoEncontradaException {
        Producto producto = buscarPorId(id);
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        producto.setNombre(nuevoNombre);
    }

    // Elimina un producto de la lista
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Producto p = buscarPorId(id);
        productos.remove(p);
    }
}
