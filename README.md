# Simulador de Supermercado - Desarrollo de Software Seguro

Este proyecto es una aplicación Java diseñada para simular el proceso de cobro en un supermercado utilizando programación multihilo (Threads). Forma parte del **Taller 2** de la asignatura de **Desarrollo de Software Seguro**.

## 🚀 Descripción del Proyecto

La aplicación simula un entorno de supermercado donde múltiples cajeras procesan las compras de varios clientes de manera simultánea. El objetivo principal es demostrar el uso de hilos en Java para mejorar la eficiencia y el tiempo de respuesta en procesos que pueden ejecutarse en paralelo.

## ✨ Características Principales

- **Concurrencia Real:** Implementación de hilos independientes para cada cajera.
- **Interfaz Gráfica (GUI):** Construida con Java Swing, ofreciendo una visualización clara y moderna del proceso.
- **Cálculo de Tiempos:** Seguimiento preciso del tiempo de procesamiento por producto y tiempo total de la simulación.
- **Gestión de Costos:** Cálculo automático del costo total por cliente basado en los precios de los productos.
- **Logs en Tiempo Real:** Visualización detallada de cada paso del proceso de cobro.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 17
- **Interfaz Gráfica:** Java Swing / AWT
- **Gestión de Proyectos:** Maven
- **Concurrencia:** Java Threads

## 📂 Estructura del Proyecto

```text
src/main/java/com/ias/supermercado/
├── logic/
│   └── Cajera.java           # Lógica de los hilos y procesamiento
├── model/
│   ├── Cliente.java         # Modelo de datos del cliente
│   └── Producto.java        # Modelo de datos del producto
├── view/
│   └── SimuladorFrame.java  # Interfaz gráfica de usuario
└── MainSupermercado.java    # Punto de entrada de la aplicación
```

## 🏃 Cómo Ejecutar

1. Asegúrate de tener instalado **Java JDK 17** o superior.
2. Clona o descarga el proyecto.
3. Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse, VS Code).
4. Ejecuta la clase `MainSupermercado.java`.
5. Haz clic en el botón **"Iniciar Simulación"** para comenzar el proceso.

---
**Desarrollado como parte del Taller de Software Seguro.**
