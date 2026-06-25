
package src.entities;

public class Producto extends Base {

    // Atributos
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    // Constructor
    public Producto(Long id,
                    String nombre,
                    Double precio,
                    String descripcion,
                    int stock,
                    String imagen,
                    boolean disponible,
                    Categoria categoria) {

        super(id); // La llamada a super() debe ser la primera instrucción del constructor.
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setStock(stock);
        setImagen(imagen);
        this.disponible = true;
        setCategoria(categoria);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo");
        }
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        if (precio < 0){
            throw new IllegalArgumentException("el precio del producto no puede ser negativo");
        }
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion == null){
            throw new IllegalArgumentException("la descripcion del producto no puede ser nula");
        }
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock <0){
            throw new IllegalArgumentException("el stock no puede ser valor negativo");
        }
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return stock > 0;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public String resumen() {
    return "ID: " + getId() + " - Nombre: " + nombre;
}

    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Producto ID: " + getId() + 
            " | Nombre: " + nombre+
            " | Precio: " + precio;
    }
    
}
