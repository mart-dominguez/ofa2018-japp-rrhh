/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.frsf.ofa.cursojava.dao.ConexionJPA;

/**
 *
 * @author mdominguez
 */
public class Laboratorio07Test {
    
    private EntityManager em;
    public Laboratorio07Test() {
    }
    
    @Before
    public void setUp() {
        this.em = ConexionJPA.get();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void crearProyetoCliente(){
        em.getTransaction().begin();
        Proyecto p1 = new Proyecto();
        p1.setDescripcion("Detalle de tareas de proyecto 1");
        p1.setNombre("Proyecto 1");
        p1.setPresupuestoMaximo(1000.0);
        Cliente cli1 = new Cliente();
        cli1.setNombre("Cliente 1");
        cli1.setCorreo("cli1@mail.com");
        cli1.setCuit("20301234569");
        em.persist(cli1);
        p1.setCliente(cli1);
        em.persist(p1);
        this.em.getTransaction().commit();
        // buscarproyecto por nombre
        Proyecto p2 = (Proyecto) em.createQuery("SELECT p FROM Proyecto p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Proyecto 1").getSingleResult();
        assertEquals(p1.getNombre(), p2.getNombre());
        assertEquals(p1.getCliente().getNombre(), p2.getCliente().getNombre());
    }
    
    
}
