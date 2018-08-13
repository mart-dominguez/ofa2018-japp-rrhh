/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.List;
import utn.frsf.ofa.cursojava.lab04.Cliente;

/**
 *
 * @author mdominguez
 */
public interface ClienteDao {
    public void crear(Cliente e);
    public void actualizar(Cliente e);
    public void eliminar(Cliente e);
    public Cliente buscarPorId(Integer id);
    public List<Cliente> buscarTodos();
}
