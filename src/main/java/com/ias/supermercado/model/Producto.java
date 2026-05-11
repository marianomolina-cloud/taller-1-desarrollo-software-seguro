package com.ias.supermercado.model;

public class Producto {
    private String nombre;
    private double precio;
    private int tiempoSegundos;

    public Producto(String nombre, double precio, int tiempoSegundos) {
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoSegundos = tiempoSegundos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}
