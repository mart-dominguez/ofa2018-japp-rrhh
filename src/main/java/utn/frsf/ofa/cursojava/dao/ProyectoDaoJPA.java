/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import utn.frsf.ofa.cursojava.lab04.Proyecto;

/**
 *
 * @author marti
 */
public class ProyectoDaoJPA implements ProyectoDao {
    
    private EntityManager em;    
    
    @Override
    public void crear(Proyecto e) {
        this.em = ConexionJPA.get();
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
    public void actualizar(Proyecto e) {
        this.em = ConexionJPA.get();
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
    public void eliminar(Proyecto e) {
        this.em = ConexionJPA.get();
        try {
            em.getTransaction().begin();
            Proyecto p = em.find(Proyecto.class, e.getId());
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception ex) {
            
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Proyecto buscarPorId(Integer id) {
        return em.find(Proyecto.class, id);
    }
    
    @Override
    public List<Proyecto> buscarTodos() {
        this.em = ConexionJPA.get();
        List<Proyecto> resultado = new ArrayList<Proyecto>();
        try {
            em.getTransaction().begin();
            resultado = this.em.createQuery("SELECT p FROM Proyecto p").getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return resultado;
    }
    
}
