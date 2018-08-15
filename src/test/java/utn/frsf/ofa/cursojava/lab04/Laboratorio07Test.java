/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import java.util.ArrayList;
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
    public void crearProyetoCliente() {
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

    @Test
    public void crearProyectoEmpleado() {
        em.getTransaction().begin();
        Proyecto p1 = new Proyecto();
        p1.setDescripcion("Detalle de tareas de proyecto 1");
        p1.setNombre("Proyecto X1");
        p1.setPresupuestoMaximo(1000.0);
        Proyecto p2 = new Proyecto();
        p2.setDescripcion("Detalle 3");
        p2.setNombre("Proyecto X2");
        p2.setPresupuestoMaximo(5000.0);
        Empleado emp1 = new Contratado();
        emp1.setCuil("999");
        emp1.setNombre("Empleado01");
        Empleado emp2 = new Efectivo();
        emp2.setCuil("555");
        emp2.setNombre("Empleado02");

        em.persist(emp1);
        em.persist(emp2);
        p1.setEmpleados(new ArrayList<>());
        p1.getEmpleados().add(emp1);
        p1.getEmpleados().add(emp2);

        p2.setEmpleados(new ArrayList<>());
        p2.getEmpleados().add(emp2);
        em.persist(p1);
        em.persist(p2);
        System.out.println("ANTES DE FLUSH DE TX ");        
        this.em.flush();
        this.em.getTransaction().commit();
        // buscarproyecto por nombre
        this.em = null;
        
        
        EntityManager em2 = ConexionJPA.get();
        Proyecto resultPry1 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X1").getSingleResult();
        System.out.println("resultPry1: "+resultPry1.toString());
        assertEquals(p1.getNombre(), resultPry1.getNombre());
        assertEquals(2, resultPry1.getEmpleados().size());
        
        Proyecto resultPry2 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE p.nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X2").getSingleResult();
        assertEquals(p2.getNombre(), resultPry2.getNombre());
        assertEquals(1, resultPry2.getEmpleados().size());
    
        Empleado empleado01 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Empleado01").getSingleResult();
        assertEquals(1, empleado01.getProyectosAsignados().size());

        // verificar que  como la relacion no la guardo el dueño no fue seteada
        Empleado empleado02 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Empleado02").getSingleResult();
        assertEquals(2, empleado02.getProyectosAsignados().size());
    }
    
    @Test
    public void verificarLadoInversoNoGuarda() {
        em.getTransaction().begin();
        Proyecto p3 = new Proyecto();
        p3.setDescripcion("Detalle de tareas de proyecto 3");
        p3.setNombre("Proyecto X3");
        p3.setPresupuestoMaximo(1000.0);
        Proyecto p4 = new Proyecto();
        p4.setDescripcion("Detalle 4");
        p4.setNombre("Proyecto X4");
        p4.setPresupuestoMaximo(5000.0);
        Empleado emp3 = new Contratado();
        emp3.setCuil("999");
        emp3.setNombre("Empleado03");
        Empleado emp4 = new Efectivo();
        emp4.setCuil("555");
        emp4.setNombre("Empleado04");
        emp3.setProyectosAsignados(new ArrayList<>());
        emp3.getProyectosAsignados().add(p3);
        emp3.getProyectosAsignados().add(p4);
        emp4.setProyectosAsignados(new ArrayList<>());
        emp4.getProyectosAsignados().add(p3);

        em.persist(p3);
        em.persist(p4);
        
        // el dueño de la relación es Proyecto por lo que para persistir la relación deberíamos guardar la asociación en proyectos.
        em.persist(emp3);
        em.persist(emp4);
        
        System.out.println("ANTES DE COMMIT DE TX ");
        this.em.getTransaction().commit();
        // buscarproyecto por nombre
        
        EntityManager em2 = ConexionJPA.get();
        Proyecto resultPry3 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X3").getSingleResult();
        assertEquals(p3.getNombre(), resultPry3.getNombre());
        assertTrue(resultPry3.getEmpleados().isEmpty());

        Proyecto resultPry4 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X4").getSingleResult();
        assertEquals(p4.getNombre(), resultPry4.getNombre());
        assertTrue(resultPry4.getEmpleados().isEmpty());

        // verificar que  como la relacion no la guardo el dueño no fue seteada
        Empleado empleado03 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Empleado03").getSingleResult();
        assertTrue(empleado03.getProyectosAsignados().isEmpty());

        // verificar que  como la relacion no la guardo el dueño no fue seteada
        Empleado empleado04 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE nombre= :P_NOMBRE").setParameter("P_NOMBRE", "Empleado04").getSingleResult();
        assertTrue(empleado04.getProyectosAsignados().isEmpty());
        
    }

}
