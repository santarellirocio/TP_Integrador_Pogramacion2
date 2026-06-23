/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import src.entities.Categoria;
import src.entities.Pedido;
import src.entities.Producto;
import src.entities.Usuario;
import src.enums.Estado;
import src.enums.FormaPago;
import src.enums.Rol;
import src.exceptions.EntidadNoEncontradaException;
import src.exceptions.StockInsuficienteException;
import src.services.CategoriaService;
import src.services.PedidoService;
import src.services.ProductoService;
import src.services.UsuarioService;

/**
 *
 * @author santa
 */
public class AppMenu {
    private Scanner scanner = new Scanner(System.in);
    private CategoriaService categoriaService;
    private ProductoService productoService;
    private UsuarioService usuarioService;
    private PedidoService pedidoService;

    // Constructor
    public AppMenu(CategoriaService cs, ProductoService prods, UsuarioService us, PedidoService ps) {
        this.categoriaService = cs;
        this.productoService = prods;
        this.usuarioService = us;
        this.pedidoService = ps;
    }
    
    public void iniciar() throws EntidadNoEncontradaException, Exception {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- SISTEMA DE PEDIDOS (FOOD STORE) ---");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            // Utilizamos switch moderno para mayor legibilidad y evitar breaks
            switch (opcion) {
                case "1" -> menuCategorias(); // Llamas al submenú Categorias
                case "2" -> menuProductos();  // Llamas al submenú Productos
                case "3" -> menuUsuarios();   // Llamas al submenú Usuarios
                case "4" -> menuPedidos();    // Llamas al submenú Pedidos
                case "0" -> {
                    System.out.println("Cerrando sistema...");
                    activo = false;
                }   
            }
        }
    }

    // --- SUBMENÚS ---
    private void menuCategorias() {
        System.out.println("\n--- GESTIÓN DE CATEGORÍAS ---");
        System.out.println("1. Listar\n2. Crear\n3. Editar \n4. Eliminar\n0. Salir");
        String op = scanner.nextLine();
        switch (op) {
            case "1" -> categoriaService.listar().forEach(System.out::println);
            case "2" -> { 
                System.out.println("--- NUEVA CATEGORIA ---");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Descripcion: ");
                String desc = scanner.nextLine();

                // Generamos un ID automático basado en cuántos elementos hay
                Long nuevoId = (long) categoriaService.listar().size() + 1;

                // Creamos el objeto
                Categoria nueva = new Categoria(nuevoId, nombre, desc);

                // Lo guardamos en el servicio
                categoriaService.crear(nueva);
                System.out.println("¡Categoría creada correctamente!");
                }
            case "3" -> {
                System.out.print("Ingrese el ID de la categoría a editar: ");
                try {
                    Long id = Long.parseLong(scanner.nextLine());
                    Categoria cat = categoriaService.buscarPorId(id); // Buscamos primero

                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();

                    cat.setNombre(nuevoNombre); // Actualizamos el objeto
                    categoriaService.editar(cat); // Guardamos los cambios
                    System.out.println("Categoría actualizada.");
                } catch (Exception e) {
                    System.out.println("Error al editar: " + e.getMessage());
                }
            }
            case "4" -> {
                System.out.print("Ingrese el ID de la categoría a eliminar: ");
                try {
                    Long id = Long.parseLong(scanner.nextLine());
                    categoriaService.eliminar(id);
                    System.out.println("Categoría eliminada.");
                } catch (NumberFormatException | EntidadNoEncontradaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "0" -> { return; } // Vuelve al menú principal
        }
    }

    private void menuProductos() throws EntidadNoEncontradaException {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Listar\n2. Crear\n3. Editar \n4. Eliminar\n0. Salir");
        String op = scanner.nextLine();
        switch (op) {
            case "1" -> productoService.listar().forEach(System.out::println);
            case "2" -> { 
                try {
                    System.out.print("Nombre: "); String nombre = scanner.nextLine();
                    System.out.print("Precio: "); Double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Stock: "); int stock = Integer.parseInt(scanner.nextLine());

                    // Listamos categorías primero para que el usuario sepa qué ID elegir
                    System.out.println("Categorías disponibles:");
                    categoriaService.listar().forEach(System.out::println);

                    System.out.print("ID de Categoria: "); 
                    Long catId = Long.parseLong(scanner.nextLine());

                    // Buscamos la categoría
                    Categoria cat = categoriaService.buscarPorId(catId); 

                    // Creamos
                    Long nuevoId = (long) productoService.listar().size() + 1;
                    productoService.crear(new Producto(nuevoId, nombre, precio, "Desc", stock, "img.jpg", true, cat));

                    System.out.println("¡Producto creado!");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor ingrese números válidos en precio/stock/ID.");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                
            }
            case "3" -> {
                try {
                    System.out.print("ID del producto a editar: "); Long id = Long.parseLong(scanner.nextLine());
                    Producto p = productoService.buscarPorId(id);
                    System.out.print("Nuevo nombre: "); p.setNombre(scanner.nextLine());
                    productoService.editar(p);
                } catch (NumberFormatException e){
                    System.out.println("Error: el id debe ser un numero");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
  
            case "4" -> {
                try {
                    System.out.print("ID a eliminar: "); Long id = Long.parseLong(scanner.nextLine());
                    productoService.eliminar(id);
                } catch (NumberFormatException e){
                    System.out.println("Error: el id debe ser un numero");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
  
            case "0" -> { return; } // Vuelve al menú principal
        }
    }

    private void menuUsuarios() throws Exception {
        System.out.println("\n--- GESTIÓN DE USUARIOS ---");
        System.out.println("1. Listar\n2. Crear\n3. Editar \n4. Eliminar\n0. Salir");
        String op = scanner.nextLine();
        switch (op) {
            case "1" -> {
                List<Usuario> lista = usuarioService.listar();
                if (lista.isEmpty()) {
                    System.out.println("No hay usuarios registrados.");
                } else {
                    System.out.println("--- LISTADO DE USUARIOS ---");
                    lista.forEach(System.out::println);
                }
            }
            case "2" -> { 
                System.out.print("Nombre: "); String nombre = scanner.nextLine();
                System.out.print("Apellido: "); String apellido = scanner.nextLine();
                System.out.print("Email: "); String mail = scanner.nextLine();
                System.out.print("Celular: "); String cel = scanner.nextLine();
                System.out.print("Contraseña: "); String contra = scanner.nextLine();
                Rol rolSeleccionado = pedirRol();
                Long nuevoId = (long) usuarioService.listar().size() + 1;
                usuarioService.crear(new Usuario(nuevoId, nombre, apellido, mail, cel, contra, rolSeleccionado));
            }
            case "3" -> {
                try{
                    System.out.print("ID de usuario a editar: "); Long id = Long.parseLong(scanner.nextLine());
                    Usuario u = usuarioService.buscarPorId(id);
                    System.out.print("Nuevo nombre: "); u.setNombre(scanner.nextLine());
                    usuarioService.editar(u);
                } catch (NumberFormatException e){
                    System.out.println("Error: el id debe ser un numero");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
  
            case "4" -> {
                try {
                    System.out.print("ID a eliminar: "); Long id = Long.parseLong(scanner.nextLine());
                    usuarioService.eliminar(id);
                } catch (NumberFormatException e){
                    System.out.println("Error: el id debe ser un numero");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
  
            case "0" -> { return; } // Vuelve al menú principal
        }
    }

    private void menuPedidos() throws EntidadNoEncontradaException {
        System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
        System.out.println("1. Listar\n2. Crear\n3. Editar \n4. Eliminar\n0. Salir");
        String op = scanner.nextLine();
        switch (op) {
            case "1" -> pedidoService.listar().forEach(System.out::println);
            case "2" -> {
                    try {
                    System.out.print("ID de Usuario: "); 
                    Long uId = Long.parseLong(scanner.nextLine());
                    Usuario u = usuarioService.buscarPorId(uId);
                    Pedido nuevo = new Pedido((long) pedidoService.listar().size() + 1, Estado.PENDIENTE, FormaPago.EFECTIVO, u);
                    // Bucle para agregar varios productos
                    boolean agregando = true;
                    while (agregando) {
                        System.out.print("ID de producto a agregar: ");
                        Long prodId = Long.parseLong(scanner.nextLine());
                        Producto p = productoService.buscarPorId(prodId);

                        System.out.print("Cantidad: ");
                        int cant = Integer.parseInt(scanner.nextLine());

                        try {
                            // Llamamos al método directamente sobre el objeto 'nuevo'
                            nuevo.addDetallePedido(cant, p); 
                            System.out.println("Producto agregado al pedido.");
                        } catch (StockInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                        System.out.print("¿Agregar otro producto? (s/n): ");
                        agregando = scanner.nextLine().equalsIgnoreCase("s");
                    }
                    pedidoService.crear(nuevo);
                    System.out.println("Pedido creado con éxito.");
                } catch (NumberFormatException e){
                     System.out.println("Error: Por favor, ingrese un número válido para los IDs o cantidades.");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error: " + e.getMessage());
    }
}
                    
                
                    
            case "3" -> { // EDITAR (Cambiar estado, por ejemplo)
                    try{
                        System.out.print("ID de pedido a editar: "); Long id = Long.parseLong(scanner.nextLine());
                        Pedido p = pedidoService.buscarPorId(id);
                        p.setEstado(Estado.CONFIRMADO); // Ejemplo de edición
                        pedidoService.editar(p);
                } catch (NumberFormatException e){
                    System.out.println("Error: el id debe ser un numero");
                } catch (EntidadNoEncontradaException e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
  
            case "4" -> {
                try{
                    System.out.print("ID de pedido a eliminar: "); Long id = Long.parseLong(scanner.nextLine());
                    pedidoService.eliminar(id);
            }  catch (NumberFormatException e){
                System.out.println("Error: el id debe ser un numero");
            } catch (EntidadNoEncontradaException e) {
                System.out.println("Error " + e.getMessage());
                }
            }
  
            case "0" -> { return; } // Vuelve al menú principal
        }
    }

    // Metodo para pedir el Rol al usuario
    private Rol pedirRol() {
    System.out.println("Seleccione el ROL:");
    System.out.println("1. ADMIN");
    System.out.println("2. USUARIO");
    
    String opcion = scanner.nextLine();
    
    // Usamos un switch para retornar el valor Enum correspondiente
    return switch (opcion) {
        case "1" -> Rol.ADMIN;
        case "2" -> Rol.USUARIO;
        default -> {
            System.out.println("Opción inválida, se asignará USUARIO por defecto.");
            yield Rol.USUARIO; // 'yield' es necesario en switch moderno para retornar valor
        }
    };
}
}
