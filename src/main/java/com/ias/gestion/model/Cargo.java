package com.ias.gestion.model;

import java.math.BigDecimal;

public class Cargo {
    private int idCargo;
    private String nombreCargo;
    private BigDecimal salarioBase;

    public Cargo() {}

    public Cargo(int idCargo, String nombreCargo, BigDecimal salarioBase) {
        this.idCargo = idCargo;
        this.nombreCargo = nombreCargo;
        this.salarioBase = salarioBase;
    }

    public int getIdCargo() { return idCargo; }
    public void setIdCargo(int idCargo) { this.idCargo = idCargo; }

    public String getNombreCargo() { return nombreCargo; }
    public void setNombreCargo(String nombreCargo) { this.nombreCargo = nombreCargo; }

    public BigDecimal getSalarioBase() { return salarioBase; }
    public void setSalarioBase(BigDecimal salarioBase) { this.salarioBase = salarioBase; }

    @Override
    public String toString() { return nombreCargo; }
}
