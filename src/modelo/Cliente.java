package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mario
 */
@Entity
@Table(name = "clientes", catalog = "venta_vehiculos")
public class Cliente implements Serializable {

    @Id
    @Column(name="dni")
    private String dni;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Vehiculo> listaVehiculos;
    
    @Column(name = "nombre", unique = false, nullable = false)
    private String nombre;
    
    @Column(name = "direccion", unique = false, nullable = false)
    private String direccion;
    
    @Column(name = "numTLF", unique = false, nullable = false)
    private int numTLF;
    
    @Column(name = "ciudad", unique = false, nullable = false)
    private String ciudad;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String direccion, int numTLF, String ciudad) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numTLF = numTLF;
        this.ciudad = ciudad;
    }
    
    public Cliente(String dni, List<Vehiculo> listaVehiculos, String nombre, String direccion, int numTLF, String ciudad) {
        this.dni = dni;
        this.listaVehiculos = listaVehiculos;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numTLF = numTLF;
        this.ciudad = ciudad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumTLF() {
        return numTLF;
    }

    public void setNumTLF(int numTLF) {
        this.numTLF = numTLF;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> lista) {
        this.listaVehiculos = lista;
    }

}
