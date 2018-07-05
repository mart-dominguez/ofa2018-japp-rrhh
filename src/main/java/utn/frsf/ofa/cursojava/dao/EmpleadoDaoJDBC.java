/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import utn.frsf.ofa.cursojava.lab04.Contratado;
import utn.frsf.ofa.cursojava.lab04.Efectivo;
import utn.frsf.ofa.cursojava.lab04.Empleado;

/**
 *
 * @author mdominguez
 */
public class EmpleadoDaoJDBC implements EmpleadoDao {
    
    private final String INSERT_EMPLEADO = "INSERT INTO EMPLEADOS (NOMBRE, CORREO, CUIL, FECHA_INGRESO, HS_TRABAJADAS, SUELDO_BASICO, COMISIONES, HS_MINIMAS, COSTO_HORA, TIPO_EMPLEADO) VALUES (?,?,?,?,?,?,?,?,?,?)";
    
    @Override
    public void crear(Empleado e) {
        Connection conn = ConexionJDBC.getConexion();        
        try(PreparedStatement ps = conn.prepareStatement(INSERT_EMPLEADO)){
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCorreoElectronico());
            ps.setString(3, e.getCuil());
            ps.setDate(4, new Date(e.getFechaIngreso().getTime()));
            ps.setInt(5, e.getHorasTrabajadas());
            if(e.esEfectivo()){
                Efectivo empEf = (Efectivo) e;
                ps.setDouble(6, empEf.getSueldoBasico());
                ps.setDouble(7, empEf.getComisiones());
                ps.setInt(8, empEf.getHorasMinimasObligatorias());
                ps.setInt(10, 1);
            }
            if(e.esContratado()){
                Contratado c = (Contratado) e;
                ps.setDouble(9, c.getCostoHora());
                ps.setInt(10, 2);
            }
            int filasInsertadas = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
