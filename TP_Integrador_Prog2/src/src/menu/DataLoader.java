/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.menu;

import java.time.LocalDate;
import src.entities.Categoria;
import src.entities.Pedido;
import src.entities.Producto;
import src.entities.Usuario;
import src.enums.Estado;
import src.enums.FormaPago;
import src.enums.Rol;
import src.exceptions.StockInsuficienteException;
import src.services.PedidoService;

/**
 *
 * @author santa
 */
public class DataLoader {
    // Metodo para cargar los datos
    public static void cargarDatos(PedidoService pedidoService) throws StockInsuficienteException {
        // --- 1. CATEGORIAS ---
        Categoria bebidas = new Categoria(1L, "Bebidas", "Bebidas frías y calientes");
        Categoria hamburguesas = new Categoria(2L, "Hamburguesas", "Hamburguesas artesanales");
        Categoria postres = new Categoria(3L, "Postres", "Postres caseros");

        // --- 2. PRODUCTOS ---
        Producto coca = new Producto(1L, "Coca Cola", 2500.0, "Gaseosa", 100, "coca.jpg", true, bebidas);
        Producto agua = new Producto(2L, "Agua Mineral", 1800.0, "Agua sin gas", 100, "agua.jpg", true, bebidas);
        Producto clasica = new Producto(3L, "Hamburguesa Clasica", 8500.0, "Carne y queso", 50, "clasica.jpg", true, hamburguesas);
        Producto doble = new Producto(4L, "Hamburguesa Doble", 10500.0, "Doble carne", 50, "doble.jpg", true, hamburguesas);
        Producto flan = new Producto(5L, "Flan", 3000.0, "Flan casero", 40, "flan.jpg", true, postres);
        Producto brownie = new Producto(6L, "Brownie", 3500.0, "Brownie con nueces", 40, "brownie.jpg", true, postres);

        // --- 3. USUARIOS ---
        Usuario admin = new Usuario(1L, "Juan", "Perez", "juan@gmail.com", "+549358355872", "abcd1234", Rol.ADMIN);
        Usuario usuario = new Usuario(2L, "Maria", "Gomez", "maria.gomez@hotmail.com", "+549358101224", "wxyz6789", Rol.USUARIO);

        // --- 4. PEDIDOS ---
        Pedido pedido1 = new Pedido(1L, LocalDate.now(), Estado.PENDIENTE, FormaPago.TARJETA, admin);
        Pedido pedido2 = new Pedido(2L, LocalDate.now(), Estado.CONFIRMADO, FormaPago.EFECTIVO, admin);
        Pedido pedido3 = new Pedido(3L, LocalDate.now(), Estado.TERMINADO, FormaPago.TRANSFERENCIA, usuario);
        Pedido pedido4 = new Pedido(4L, LocalDate.now(), Estado.PENDIENTE, FormaPago.TARJETA, usuario);

        // --- 5. DETALLES Y GUARDADO ---
        pedido1.addDetallePedido(2, coca);
        pedido1.addDetallePedido(1, clasica);
        pedido1.addDetallePedido(3, flan);
        pedidoService.guardar(pedido1);

        pedido2.addDetallePedido(1, agua);
        pedido2.addDetallePedido(2, doble);
        pedido2.addDetallePedido(1, brownie);
        pedidoService.guardar(pedido2);

        pedido3.addDetallePedido(2, coca);
        pedido3.addDetallePedido(1, doble);
        pedido3.addDetallePedido(2, brownie);
        pedidoService.guardar(pedido3);

        pedido4.addDetallePedido(3, agua);
        pedido4.addDetallePedido(1, clasica);
        pedido4.addDetallePedido(2, flan);
        pedidoService.guardar(pedido4);
    }
}
