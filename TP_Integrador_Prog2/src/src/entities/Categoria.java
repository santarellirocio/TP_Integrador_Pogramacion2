
package src.entities;

import java.util.ArrayList;
import java.util.List;


public class Categoria extends Base {

    // Atributos
    private String nombre;
    private String descripcion;
    private List<Producto> productos;// agregacion- relacion 1:N - una categoria tiene muchos productos

    // Constructores
    public Categoria(Long id, String nombre, String descripcion) {
        super(id);  // La llamada a super() debe ser la primera instrucción del constructor.
        setNombre(nombre);
        setDescripcion(descripcion);
        this.productos = new ArrayList<>();
    }
 
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null){
            throw new IllegalArgumentException("el nombre de la categoria no puede ser nulo");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null){
            throw new IllegalArgumentException("la descripcion no puede ser nula");
        }
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Producto producto) {
      if(producto != null && !productos.contains(producto)){
            productos.add(producto);
        }   
    }

    // Metodo para añadir productos
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    public String resumen() {
    return "ID: " + getId() + " - Nombre: " + nombre;
}

    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Categoria ID: " + getId() +
            " | Nombre: " + nombre + 
            " | Descripción:" + descripcion;
    }
    
}
