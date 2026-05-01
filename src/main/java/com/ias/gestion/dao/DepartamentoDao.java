package com.ias.gestion.dao;

import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Departamento;
import java.util.List;

public interface DepartamentoDao {
    List<Departamento> listar() throws DaoException;
}
