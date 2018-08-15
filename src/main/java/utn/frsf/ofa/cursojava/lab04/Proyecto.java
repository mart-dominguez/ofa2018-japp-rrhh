/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.lab04;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author marti
 */
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String nombre;
    protected String descripcion;
    protected Double presupuestoMaximo;
    
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    protected Cliente cliente;

    @ManyToMany
    @JoinTable(name = "PROYECTO_EMPLEADO",
            joinColumns = @JoinColumn(name = "ID_PROYECTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_EMPLEADO")
    )
    protected List<Empleado> empleados;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public void setPresupuestoMaximo(Double presupuestoMaximo) {
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", presupuestoMaximo=" + presupuestoMaximo + ", cliente=" + cliente + ", empleados=" + empleados + '}';
    }

    
    
    
    
}
