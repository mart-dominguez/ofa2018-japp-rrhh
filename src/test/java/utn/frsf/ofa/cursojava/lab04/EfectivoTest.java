/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdominguez
 */
public class EfectivoTest {
    
    public EfectivoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of salario method, of class Efectivo.
     */
    @Test
    public void testSalarioSinHorasExtras() {
        System.out.println("salario");
        Efectivo empleado = new Efectivo();
        empleado.setHorasTrabajadas(40);
        empleado.setHorasMinimasObligatorias(40);
        empleado.setComisiones(2000.0);
        empleado.setSueldoBasico(30000.0);
        Double expResult = 30000.0+2000.0;
        Double result = empleado.salario();
        assertEquals(expResult, result);
    }
   
        /**
     * Test of salario method, of class Efectivo.
     */
    @Test
    public void testSalario2HorasExtras() {
        System.out.println("salario");
        Efectivo empleado = new Efectivo();
        empleado.setHorasTrabajadas(42);
        empleado.setHorasMinimasObligatorias(40);
        empleado.setComisiones(2000.0);
        empleado.setSueldoBasico(30000.0);
        Double expResult = 30000.0+2000.0+3000.0;
        Double result = empleado.salario();
        assertEquals(expResult, result);
    }

    
    
}
