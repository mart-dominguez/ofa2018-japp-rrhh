/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import utn.frsf.ofa.cursojava.lab04.Cliente;
import utn.frsf.ofa.cursojava.lab04.Proyecto;

/**
 *
 * @author mdominguez
 */
public class ClienteDaoJPA implements ClienteDao {

    private EntityManager em;

    @Override
    public void crear(Cliente e) {
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
    public void actualizar(Cliente e) {
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
    public void eliminar(Cliente e) {
        this.em = ConexionJPA.get();
        try {
            em.getTransaction().begin();
            Cliente p = em.find(Cliente.class, e.getId());
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
    public Cliente buscarPorId(Integer id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        this.em = ConexionJPA.get();
        List<Cliente> resultado = new ArrayList<Cliente>();
        try {
            em.getTransaction().begin();
            resultado = this.em.createQuery("SELECT p FROM Cliente p").getResultList();
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
