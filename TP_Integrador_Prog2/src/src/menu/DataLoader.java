
package src.menu;

import src.entities.Categoria;
import src.entities.Pedido;
import src.entities.Producto;
import src.entities.Usuario;
import src.enums.Estado;
import src.enums.FormaPago;
import src.enums.Rol;
import src.exceptions.StockInsuficienteException;
import src.services.CategoriaService;
import src.services.PedidoService;
import src.services.ProductoService;
import src.services.UsuarioService;



public class DataLoader {
    // Metodo que recibe los servicios para cargar datos iniciales en ellos
    public void cargarDatos(CategoriaService catService, ProductoService prodService, UsuarioService userService, 
            PedidoService pedidoService) throws StockInsuficienteException, Exception {
        // Creamos y guardamos datos de prueba para no empezar de cero
        // --- 1. CATEGORIAS ---
        Categoria bebidas = new Categoria(1L, "Bebidas", "Bebidas frías y calientes");
        Categoria congelados = new Categoria(2L, "congelados", "productos refrigerados");
        Categoria lacteos = new Categoria(3L, "lacteos", "productos derivados de la leche y sus alternativas vegetales");
        
        //se almacena
        catService.crear(bebidas);
        catService.crear(congelados);
        catService.crear(lacteos);
        
        // --- 2. PRODUCTOS ---
        Producto coca = new Producto(1L, "Coca Cola", 2500.0, "Gaseosa", 100, "coca.jpg", true, bebidas);
        Producto agua = new Producto(2L, "Agua Mineral", 1800.0, "Agua sin gas", 100, "agua.jpg", true, bebidas);
        Producto hamburguesa = new Producto(3L, "Hamburguesa Clasica", 8500.0, "Carne y queso", 50, "clasica.jpg", true, congelados);
        Producto helado = new Producto(4L, "helado", 10500.0, "4 sabores", 50, "helado.jpg", true, congelados);
        Producto leche = new Producto(5L, "leche", 3000.0, "descremada", 40, "flan.jpg", true, lacteos);
        Producto yogur = new Producto(6L, "yogur", 3500.0, "griego sin azucar", 40, "yogur.jpg", true, lacteos);
        
        prodService.crear(coca);
        prodService.crear(agua);
        prodService.crear(hamburguesa);
        prodService.crear(helado);
        prodService.crear(leche);
        prodService.crear(yogur);       
        
        
        // --- 3. USUARIOS ---
        Usuario admin = new Usuario(1L, "Juan", "Perez", "juan@gmail.com", "+549358355872", "abcd1234", Rol.ADMIN);
        Usuario usuario = new Usuario(2L, "Maria", "Gomez", "maria.gomez@hotmail.com", "+549358101224", "wxyz6789", Rol.USUARIO);
        //se almacenan
        userService.crear(usuario);
        userService.crear(admin);
        

        // --- 4. PEDIDOS ---
        Pedido pedido1 = new Pedido(1L, Estado.PENDIENTE, FormaPago.TARJETA, admin);
        Pedido pedido2 = new Pedido(2L, Estado.CONFIRMADO, FormaPago.EFECTIVO, admin);
        Pedido pedido3 = new Pedido(3L, Estado.TERMINADO, FormaPago.TRANSFERENCIA, usuario);
        Pedido pedido4 = new Pedido(4L, Estado.PENDIENTE, FormaPago.TARJETA, usuario);
        
        

        // --- 5. DETALLES Y GUARDADO ---
        pedido1.addDetallePedido(2, coca);
        pedido1.addDetallePedido(1, agua);
        pedido1.addDetallePedido(3, hamburguesa);
        pedidoService.crear(pedido1);

        pedido2.addDetallePedido(1, agua);
        pedido2.addDetallePedido(2, hamburguesa);
        pedido2.addDetallePedido(1, helado);
        pedidoService.crear(pedido2);

        pedido3.addDetallePedido(2, coca);
        pedido3.addDetallePedido(1, leche);
        pedido3.addDetallePedido(2, yogur);
        pedidoService.crear(pedido3);

        pedido4.addDetallePedido(3, agua);
        pedido4.addDetallePedido(1, yogur);
        pedido4.addDetallePedido(2, leche);
        pedidoService.crear(pedido4);
        
        
        
    }
}
