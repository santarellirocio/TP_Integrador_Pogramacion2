/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src;

import src.exceptions.StockInsuficienteException;
import src.menu.AppMenu;
import src.menu.DataLoader;
import src.services.CategoriaService;
import src.services.PedidoService;
import src.services.ProductoService;
import src.services.UsuarioService;


/**
 *
 * @author santa
 */
public class Main {

    public static void main(String[] args) throws StockInsuficienteException, Exception {
        // 1. Creamos los servicios (la base de datos en memoria)
        CategoriaService catService = new CategoriaService();
        ProductoService prodService = new ProductoService();
        UsuarioService userService = new UsuarioService();
        PedidoService pedidoService = new PedidoService();

        // 2. Cargamos datos iniciales (opcional, pero ayuda a testear)
        DataLoader loader = new DataLoader();
        // Opcional: puedes llamar a cargarDatos aquí si quieres tener datos al arrancar
        
        
        // 3. Lanzamos el menú pasándole todos los servicios inyectados
        AppMenu menu = new AppMenu(catService, prodService, userService, pedidoService);
        menu.iniciar();
    }
}
