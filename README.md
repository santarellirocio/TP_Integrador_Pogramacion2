# TP Integrador - Programación 2

## Integrantes

- Jeremías 
- Pablo Vera - Comisión 13
- Rocío Santarelli - Comisión 7

## Descripción

Sistema de gestión de pedidos desarrollado en Java para una tienda de alimentos (Food Store).

Permite administrar categorías, productos, usuarios y pedidos mediante una interfaz de consola.

## Funcionalidades

- Gestión de categorías
- Gestión de productos
- Gestión de usuarios
- Gestión de pedidos
- Asociación de productos a pedidos
- Cálculo automático del total de pedidos
- Validaciones de negocio
- Manejo de excepciones personalizadas
- Baja lógica de registros

## Conceptos aplicados

### Programación Orientada a Objetos

- Encapsulamiento
- Herencia
- Polimorfismo
- Composición
- Interfaces

### Estructuras utilizadas

- Listas (`ArrayList`)
- Enumeraciones (`enum`)

### Manejo de errores

- EntidadNoEncontradaException
- StockInsuficienteException

## Estructura del proyecto

### entities

Contiene las entidades principales:

- Base
- Usuario
- Categoria
- Producto
- Pedido
- DetallePedido

### services

Contiene la lógica de negocio:

- UsuarioService
- CategoriaService
- ProductoService
- PedidoService

### menu

Interfaz de usuario por consola:

- AppMenu
- DataLoader

### exceptions

Excepciones personalizadas.

### enums

Tipos controlados del sistema:

- Estado
- FormaPago
- Rol

## Tecnologías utilizadas

- Java
- NetBeans
- Git
- GitHub

## Requisitos

Para ejecutar el proyecto es necesario tener instalado:

* Java JDK 17 o superior.
* NetBeans IDE (o cualquier IDE compatible con proyectos Java).

## Ejecución del proyecto

1. Clonar el repositorio:
En caso de no tener SSH
bash
git clone https://github.com/santarellirocio/TP_Integrador_Pogramacion2.git

En caso de tener SSH
bash
git clone https://github.com/santarellirocio/TP_Integrador_Pogramacion2.git

2. Abrir el proyecto en NetBeans (o en cualquier IDE compatible con Java).

3. Ejecutar la clase Main.java.
