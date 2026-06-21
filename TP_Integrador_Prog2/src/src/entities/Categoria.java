/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santa
 */
public class Categoria extends Base {

    // Atributos
    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    // Constructor
    public Categoria(Long id, String nombre, String descripcion) {
        super(id);  // La llamada a super() debe ser la primera instrucción del constructor.
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Metodo para añadir productos
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Categoria ID: " + getId() +
            " | Nombre: " + nombre + 
            " | Descripción:" + descripcion;
    }
    
}
