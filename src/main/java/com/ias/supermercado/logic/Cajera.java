package com.ias.supermercado.logic;

import com.ias.supermercado.model.Cliente;
import com.ias.supermercado.model.Producto;

import java.util.function.Consumer;

public class Cajera extends Thread {
    private String nombre;
    private Cliente cliente;
    private long initialTime;
    private Consumer<String> logger;

    public Cajera(String nombre, Cliente cliente, long initialTime, Consumer<String> logger) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
        this.logger = logger;
    }

    @Override
    public void run() {
        log("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " 
            + cliente.getNombre() + " EN EL TIEMPO: " 
            + (System.currentTimeMillis() - initialTime) / 1000 + " seg");

        for (Producto producto : cliente.getProductos()) {
            this.esperarXsegundos(producto.getTiempoSegundos());
            log("Procesado el producto: " + producto.getNombre() + " -> Costo: $" + producto.getPrecio() 
                + " | Tiempo: " + (System.currentTimeMillis() - initialTime) / 1000 + " seg");
        }

        log("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " 
            + cliente.getNombre() + " EN EL TIEMPO: " 
            + (System.currentTimeMillis() - initialTime) / 1000 + " seg");
        
        log("Total compra " + cliente.getNombre() + ": $" + cliente.calcularTotal());
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void log(String message) {
        if (logger != null) {
            logger.accept(message);
        } else {
            System.out.println(message);
        }
    }
}
