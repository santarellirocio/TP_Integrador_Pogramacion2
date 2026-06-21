/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.menu;

import java.util.List;
import java.util.Scanner;
import src.entities.Pedido;
import src.exceptions.EntidadNoEncontradaException;
import src.services.PedidoService;

/**
 *
 * @author santa
 */
public class AppMenu {
    private Scanner scanner = new Scanner(System.in);
    private PedidoService pedidoService = new PedidoService();

    // Constructor
    public AppMenu(PedidoService pedidoService) {
        this.pedidoService = pedidoService; 
    }
    
    public void iniciar() {
        boolean activo = true;
        while (activo) {
            System.out.println("\n--- MENÚ FOOD STORE ---");
            System.out.println("1. Listar Pedidos");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            String opcion = scanner.nextLine();

            // Utilizamos switch moderno para mayor legibilidad y evitar breaks
            switch (opcion) {
                case "1" -> listar();
                case "2" -> eliminar();
                case "3" -> activo = false;
                default  -> System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private void listar() {
        System.out.println("\n--- LISTADO DE PEDIDOS ---");
        List<Pedido> lista = pedidoService.listarActivos();
        // Validación de estado de colección para mejorar la experiencia de usuario
        if (lista.isEmpty()) {
            System.out.println("No hay pedidos activos.");
        } else {
            for (Pedido p : lista) {
            System.out.println(p.toString());   
            }
        }
    }

    private void eliminar() {
        System.out.print("Ingrese el ID del pedido a eliminar: ");
        try {
            // Parseo seguro: prevenimos que el programa falle si el usuario ingresa letras
            Long id = Long.parseLong(scanner.nextLine());
            // Llamada al service que maneja la lógica de negocio (baja lógica)
            pedidoService.eliminarLogico(id);
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número válido.");
        } catch (EntidadNoEncontradaException e) {
            // Captura de excepción propia para un manejo de errores robusto
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
