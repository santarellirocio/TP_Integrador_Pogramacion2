
package src.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import src.entities.Categoria;
import src.exceptions.EntidadNoEncontradaException;


public class CategoriaService {
    // Lista interna donde guardamos las categorías en memoria
    private List<Categoria> categorias = new ArrayList<>();

    // Devuelve todas las categorías para poder mostrarlas
    public List<Categoria> listar() { 
        return Collections.unmodifiableList(categorias);
    }
    
    // Agrega una nueva categoría a la lista
    public void crear(Categoria c) { 
        if (c.getNombre() == null || c.getNombre().isBlank()|| c.getDescripcion() == null || c.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("se deben completar ambos campos solilcitados");
    }   
        categorias.add(c); 
    }
    
    // Actualiza la información de una categoria existente
    public void editar(Long id, String nombre, String descripcion)throws EntidadNoEncontradaException {
        Categoria categoria = buscarPorId(id);
        if (nombre == null || nombre.isBlank() || descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("debe completar todos los campos");
        }
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
}

    // Método de utilidad para encontrar un elemento por su ID
    public Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Categoría no encontrada"));
    }
    
    // Busca y elimina una categoría; lanza excepción si no existe
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(id);
        categorias.remove(c);
    }
    
}
