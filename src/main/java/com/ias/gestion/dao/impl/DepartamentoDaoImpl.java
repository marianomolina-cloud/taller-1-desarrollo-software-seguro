package com.ias.gestion.dao.impl;

import com.ias.gestion.dao.DepartamentoDao;
import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Departamento;
import com.ias.gestion.util.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoImpl implements DepartamentoDao {
    @Override
    public List<Departamento> listar() throws DaoException {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT id_departamento, nombre_departamento FROM departamentos";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                departamentos.add(new Departamento(
                    rs.getInt("id_departamento"),
                    rs.getString("nombre_departamento")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar departamentos", e);
        }
        return departamentos;
    }
}
