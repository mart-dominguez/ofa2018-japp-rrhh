/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.List;
import utn.frsf.ofa.cursojava.lab04.Empleado;

/**
 *
 * @author mdominguez
 */
public interface EmpleadoDao {
    public void crear(Empleado e);
    public void actualizar(Empleado e);
    public void eliminar(Empleado e);
    public Empleado buscarPorId(Integer id);    
    public List<Empleado> buscarTodos();
}
