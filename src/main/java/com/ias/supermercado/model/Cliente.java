package com.ias.supermercado.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private List<Producto> productos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " (" + productos.size() + " productos)";
    }
}
