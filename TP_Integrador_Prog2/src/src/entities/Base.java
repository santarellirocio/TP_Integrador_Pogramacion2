/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.entities;

import java.time.LocalDateTime;

/**
 *
 * @author santa
 */
public abstract class Base {
    // Atributos
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    // 1. Constructor vacío 
    public Base() {
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }
    
    // 2. Constructor con ID
    public Base(Long id) {
        this(); // Llama al constructor vacío de arriba
        this.id = id;
    }
    
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Metodo abstracto que Obliga a todas las clases que heredan de Base (clases hijas) a implementar su propia representación en texto.
    @Override
    public abstract String toString(); // Devuelve una cadena con la información de la entidad.
}
