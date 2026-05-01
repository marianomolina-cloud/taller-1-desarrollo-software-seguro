package com.ias.gestion.dao;

import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Funcionario;
import java.util.List;

public interface FuncionarioDao {
    void crear(Funcionario funcionario) throws DaoException;
    List<Funcionario> listar() throws DaoException;
    void actualizar(Funcionario funcionario) throws DaoException;
    void eliminar(int id) throws DaoException;
}
