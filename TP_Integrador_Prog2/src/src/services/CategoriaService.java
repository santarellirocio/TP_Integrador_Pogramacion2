/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.Categoria;
import src.exceptions.EntidadNoEncontradaException;

/**
 *
 * @author santa
 */
public class CategoriaService {
    // Lista interna donde guardamos las categorías en memoria
    private List<Categoria> categorias = new ArrayList<>();

    // Devuelve todas las categorías para poder mostrarlas
    public List<Categoria> listar() { 
        return categorias; 
    }
    
    // Agrega una nueva categoría a la lista
    public void crear(Categoria c) { 
        categorias.add(c); 
    }
    
    // Actualiza la información de una categoria existente
    public void editar(Categoria categoriaActualizada) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(categoriaActualizada.getId());
        categorias.remove(c);
        categorias.add(categoriaActualizada);
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
