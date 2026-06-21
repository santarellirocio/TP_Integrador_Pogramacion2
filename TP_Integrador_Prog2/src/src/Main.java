/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package src;

import src.exceptions.StockInsuficienteException;
import src.menu.AppMenu;
import src.menu.DataLoader;
import src.services.PedidoService;


/**
 *
 * @author santa
 */
public class Main {

    public static void main(String[] args) throws StockInsuficienteException {
        // 1. Iniciamos el Service (el cerebro)
        PedidoService pedidoService = new PedidoService();
        
        // 2. Cargamos los datos desde la clase auxiliar
        DataLoader.cargarDatos(pedidoService);
        
        // 3. Iniciamos el Menú
        AppMenu menu = new AppMenu(pedidoService);
        menu.iniciar();
    }
}
