package com.ias.gestion.model;

public class Funcionario {
    private int idFuncionario;
    private String nombre;
    private String apellido;
    private String documento;
    private String correo;
    private String telefono;
    private int idCargo;
    private String nombreCargo; // Nuevo campo
    private int idDepartamento;
    private String nombreDepartamento; // Nuevo campo

    public Funcionario() {}

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getIdCargo() { return idCargo; }
    public void setIdCargo(int idCargo) { this.idCargo = idCargo; }

    public int getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(int idDepartamento) { this.idDepartamento = idDepartamento; }

    public String getNombreCargo() { return nombreCargo; }
    public void setNombreCargo(String nombreCargo) { this.nombreCargo = nombreCargo; }

    public String getNombreDepartamento() { return nombreDepartamento; }
    public void setNombreDepartamento(String nombreDepartamento) { this.nombreDepartamento = nombreDepartamento; }
}
