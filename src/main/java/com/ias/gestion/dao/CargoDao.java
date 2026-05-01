package com.ias.gestion.dao;

import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Cargo;
import java.util.List;

public interface CargoDao {
    List<Cargo> listar() throws DaoException;
}
