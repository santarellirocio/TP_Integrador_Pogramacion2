
package src.entities;

import java.util.ArrayList;
import java.util.List;
import src.enums.Rol;

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
        setNombre(nombre);
        setApellido(apellido);
        setMail(mail);
        setCelular(celular);
        setContrasenia(contrasenia);
        setRol(rol);
        this.pedidos = new ArrayList<>();
    }

    //setters

    public void setNombre(String nombre) {
         if (nombre == null){
            throw new IllegalArgumentException("el nombre de usuario no puede ser nulo");
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido == null){
            throw new IllegalArgumentException("el apellido de usuario no puede ser nulo");
        }
        this.apellido = apellido;
    }

    public void setMail(String mail) {
        if (mail== null){
            throw new IllegalArgumentException("el mail de usuario no puede ser nulo");
        }
        this.mail = mail;
    }

    public void setCelular(String celular) {
        if (celular== null){
            throw new IllegalArgumentException("se requiere numero de celular del ususario");
        }
        this.celular = celular;
    }

    public void setContrasenia(String contrasenia) {
        if (contrasenia== null){
            throw new IllegalArgumentException("la contraseña no puede ser nula");
        }
        this.contrasenia = contrasenia;
    }

    public void setRol(Rol rol) {
        if (rol == null){
            throw new IllegalArgumentException("el rol no puede ser nulo");
        }
        this.rol = rol;
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
        //validacion
        if (pedido == null){
            throw new IllegalArgumentException ("El pedido no puede ser nulo");
        }
        //evitar duplicados
        if (!pedidos.contains(pedido)){
            pedidos.add(pedido);
        }
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
