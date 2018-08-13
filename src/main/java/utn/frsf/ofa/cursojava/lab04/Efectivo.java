/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

/**
 *
 * @author mdominguez
 */
@DiscriminatorValue(value = "2")
public class Efectivo extends Empleado{

    private Double sueldoBasico;
    private Double comisiones;
    /**
     * Cantidad de horas minimas obligatorias que tiene que cumplir el empleado
     */
    private Integer horasMinimasObligatorias;
    
    @Override
    public Double salario() {
        //el salario es igual a la suma de (1) + (2) + (3) donde
        // (1) es el sueldo básico
        // (2) son las comisiones
        // (3) es el producto de las horas extras realizadas multiplicado por el costo del sueldo básico dividido 21 dias.
        // las horas extras realizadas se calcula restando las horasTrabajadas - horasMinimasObligatorias
        //return 0.0;
        Double montoHorasExtras = 0.0;
        if(this.horasMinimasObligatorias<this.horasTrabajadas) montoHorasExtras = (sueldoBasico/20) * (this.horasTrabajadas-this.horasMinimasObligatorias);
        return this.sueldoBasico+this.comisiones+montoHorasExtras;
    }

    public Double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double getComisiones() {
        return comisiones;
    }

    public void setComisiones(Double comisiones) {
        this.comisiones = comisiones;
    }

    public Integer getHorasMinimasObligatorias() {
        return horasMinimasObligatorias;
    }

    public void setHorasMinimasObligatorias(Integer horasMinimasObligatorias) {
        this.horasMinimasObligatorias = horasMinimasObligatorias;
    }

    @Override
    public Boolean esContratado() {
        return false;
    }

    @Override
    public Boolean esEfectivo() {
        return true;
    }
    
}
