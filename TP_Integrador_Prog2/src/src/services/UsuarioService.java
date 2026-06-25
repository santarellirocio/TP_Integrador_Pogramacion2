
package src.services;

import java.util.ArrayList;
import java.util.List;
import src.entities.Usuario;
import src.exceptions.EntidadNoEncontradaException;


public class UsuarioService {
    
    private List<Usuario> usuarios = new ArrayList<>();

    // Devuelve todos los usuarios disponibles
    public List<Usuario> listar() {
        List<Usuario> activos = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) activos.add(u);
        }
        return activos;
    }
    
    // Agrega un usuario nuevo a la lista
    public void crear(Usuario u) throws Exception {
        // Validación de unicidad de email
        for (Usuario usuario : usuarios) {
            if (usuario.getMail().equalsIgnoreCase(u.getMail())) {
                throw new Exception("Ya existe un usuario con el email: " + u.getMail());
            }
        }
        usuarios.add(u);
    }

    // Actualiza la información de un producto existente
    public void editar(Usuario usuarioActualizado) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(usuarioActualizado.getId());
        int index = usuarios.indexOf(u);
        usuarios.set(index, usuarioActualizado);
    }
    
    
    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("Usuario no encontrado con ID: " + id);
    }

    // Elimina un usuario de la lista
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        u.setEliminado(true);
        System.out.println("Usuario " + u.getNombre() + " dado de baja correctamente.");
    }
}
