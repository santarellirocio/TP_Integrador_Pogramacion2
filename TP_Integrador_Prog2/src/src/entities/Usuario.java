/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.entities;

import java.util.ArrayList;
import java.util.List;
import src.enums.Rol;

/**
 *
 * @author santa
 */
public class Usuario extends Base {

    // Atributos
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    private List<Pedido> pedidos;

    // Constructor
    public Usuario(Long id, String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        super(id); // La llamada a super() debe ser la primera instrucción del constructor.
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.pedidos = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    // Metodo para añadir pedidos
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    // Sobrescritura e Implementación obligatoria del método abstracto toString() heredado de Base.
    @Override
    public String toString() {
        return "Usuario ID: " + getId() + 
            " | Nombre: " + nombre + 
            " | Apellido: " + apellido + 
            " | Rol: " + rol;
    }
    
}
