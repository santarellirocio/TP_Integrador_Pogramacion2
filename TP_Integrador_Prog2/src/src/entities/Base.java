
package src.entities;

import java.time.LocalDateTime;

public abstract class Base {
    // Atributos
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    public Base(Long id) {        
        setId(id);
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }
       
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null){
        throw new IllegalArgumentException("el id no puede ser nulo");
    }
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    
    // Metodo abstracto que Obliga a todas las clases que heredan de Base (clases hijas) a implementar su propia representación en texto.
    @Override
    public abstract String toString(); // Devuelve una cadena con la información de la entidad.
}
