package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehiculos", catalog = "venta_vehiculos")
public class Vehiculo implements Serializable {

    @Id
    @Column(name="numBastidor")
    private String numBastidor;
    
    @Column(name = "marca", unique = false, nullable = false)
    private String marca;
    
    @Column(name = "modelo", unique = false, nullable = false)
    private String modelo;
    
    @Column(name = "color", unique = false, nullable = false)
    private String color;
    
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", nullable = true)
    private Cliente cliente;
    
    @Column(name = "precioCompra", unique = false, nullable = false)
    private float precioCompra;

    public Vehiculo() {
    }

    public Vehiculo(String numBastidor, String marca, String modelo, String color, float precioCompra) {
        this.numBastidor = numBastidor;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioCompra = precioCompra;
    }

    public Vehiculo(String numBastidor, String marca, String modelo, String color, float precioCompra, Cliente cliente) {
        this.numBastidor = numBastidor;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioCompra = precioCompra;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumBastidor() {
        return numBastidor;
    }

    public void setNumBastidor(String numBastidor) {
        this.numBastidor = numBastidor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioAlquiler) {
        this.precioCompra = precioAlquiler;
    }

}
