package com.ias.gestion.dao.impl;

import com.ias.gestion.dao.FuncionarioDao;
import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Funcionario;
import com.ias.gestion.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {

    @Override
    public void crear(Funcionario f) throws DaoException {
        String sql = "INSERT INTO funcionarios (nombre, apellido, documento, correo, telefono, id_cargo, id_departamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNombre());
            ps.setString(2, f.getApellido());
            ps.setString(3, f.getDocumento());
            ps.setString(4, f.getCorreo());
            ps.setString(5, f.getTelefono());
            ps.setInt(6, f.getIdCargo());
            ps.setInt(7, f.getIdDepartamento());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error al crear funcionario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Funcionario> listar() throws DaoException {
        List<Funcionario> list = new ArrayList<>();
        String sql = "SELECT f.*, c.nombre_cargo, d.nombre_departamento " +
                     "FROM funcionarios f " +
                     "JOIN cargos c ON f.id_cargo = c.id_cargo " +
                     "JOIN departamentos d ON f.id_departamento = d.id_departamento";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setNombre(rs.getString("nombre"));
                f.setApellido(rs.getString("apellido"));
                f.setDocumento(rs.getString("documento"));
                f.setCorreo(rs.getString("correo"));
                f.setTelefono(rs.getString("telefono"));
                f.setIdCargo(rs.getInt("id_cargo"));
                f.setNombreCargo(rs.getString("nombre_cargo"));
                f.setIdDepartamento(rs.getInt("id_departamento"));
                f.setNombreDepartamento(rs.getString("nombre_departamento"));
                list.add(f);
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar funcionarios", e);
        }
        return list;
    }

    @Override
    public void actualizar(Funcionario f) throws DaoException {
        String sql = "UPDATE funcionarios SET nombre=?, apellido=?, documento=?, correo=?, telefono=?, id_cargo=?, id_departamento=? WHERE id_funcionario=?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNombre());
            ps.setString(2, f.getApellido());
            ps.setString(3, f.getDocumento());
            ps.setString(4, f.getCorreo());
            ps.setString(5, f.getTelefono());
            ps.setInt(6, f.getIdCargo());
            ps.setInt(7, f.getIdDepartamento());
            ps.setInt(8, f.getIdFuncionario());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error al actualizar funcionario: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(int id) throws DaoException {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error al eliminar funcionario", e);
        }
    }
}
