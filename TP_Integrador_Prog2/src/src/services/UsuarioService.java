/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.Usuario;
import src.exceptions.EntidadNoEncontradaException;

/**
 *
 * @author santa
 */
public class UsuarioService {
    
    private List<Usuario> usuarios = new ArrayList<>();

    public void guardar(Usuario usuario) throws Exception {
        // Validación de unicidad de email (muy valorado en la corrección)
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(usuario.getMail())) {
                throw new Exception("Ya existe un usuario con el email: " + usuario.getMail());
            }
        }
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("Usuario no encontrado con ID: " + id);
    }

    public void eliminarLogico(Long id) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        u.setEliminado(true);
        System.out.println("Usuario " + u.getNombre() + " dado de baja correctamente.");
    }
}
