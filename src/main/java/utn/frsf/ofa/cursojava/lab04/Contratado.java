/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import java.util.Date;
import javax.persistence.DiscriminatorValue;

/**
 *
 * @author mdominguez
 */
@DiscriminatorValue(value = "1")
public class Contratado extends Empleado{
    private Double costoHora;

    public Double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Double costoHora) {
        this.costoHora = costoHora;
    }

    @Override
    public Double salario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean esContratado() {
        return true;
    }

    @Override
    public Boolean esEfectivo() {
        return false;
    }

    
    
    
}
