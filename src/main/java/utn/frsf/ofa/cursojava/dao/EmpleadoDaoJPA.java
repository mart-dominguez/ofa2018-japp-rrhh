/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.List;
import javax.persistence.EntityManager;
import utn.frsf.ofa.cursojava.lab04.Empleado;

/**
 *
 * @author mdominguez
 */
public class EmpleadoDaoJPA implements EmpleadoDao {

    private EntityManager em; 
    public EmpleadoDaoJPA(){
        this.em = ConexionJPA.get();
    }
    
    @Override
    public void crear(Empleado e) {
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Empleado e) {
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Empleado e) {
        try {            
            em.getTransaction().begin();
            Empleado emp = em.find(Empleado.class, e.getId());            
            em.remove(emp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        Empleado emp = em.find(Empleado.class, id);
        em.close();
        return emp;
    }

    @Override
    public List<Empleado> buscarTodos() {
        return em.createQuery("SELECT e FROM Empleado e").getResultList();
    }

}
