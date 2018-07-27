/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.frsf.ofa.cursojava.dao.EmpleadoDao;
import utn.frsf.ofa.cursojava.dao.ProyectoDao;
import utn.frsf.ofa.cursojava.dao.ProyectoDaoJPA;

public class ProyectoTest {
    
    ProyectoDao proyectoDao;
    public ProyectoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        proyectoDao = new ProyectoDaoJPA();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salario method, of class Efectivo.
     */
    @Test
    public void testCrearProyecto() {
        List<Proyecto> lista = proyectoDao.buscarTodos();
        int proyectos = lista.size();
        Proyecto pry = new Proyecto();
        pry.setId(99);
        pry.setNombre("Proyecto 1");
        pry.setDescripcion("Descripcion proyecto 1");
        pry.setPresupuestoMaximo(999.0);
        proyectoDao.crear(pry);
        lista = proyectoDao.buscarTodos();
        int proyectosMas1 = lista.size();
        assertEquals(proyectos+1, proyectosMas1);
    }        
    
}
