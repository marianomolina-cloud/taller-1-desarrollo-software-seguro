package com.ias.gestion.dao.impl;

import com.ias.gestion.dao.CargoDao;
import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Cargo;
import com.ias.gestion.util.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImpl implements CargoDao {
    @Override
    public List<Cargo> listar() throws DaoException {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT id_cargo, nombre_cargo, salario_base FROM cargos";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cargos.add(new Cargo(
                    rs.getInt("id_cargo"),
                    rs.getString("nombre_cargo"),
                    rs.getBigDecimal("salario_base")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Error al listar cargos", e);
        }
        return cargos;
    }
}
