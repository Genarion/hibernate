/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Cliente;
import modelo.Vehiculo;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mario
 */
public class HibernateConnectionManager {

    private Session ss = NewHibernateUtil.getSessionFactory().openSession();
    private static HibernateConnectionManager hcm = null;

    public static HibernateConnectionManager hibernateConnectionManager() {
        if (hcm != null) {
            return hcm;
        } else {
            hcm = new HibernateConnectionManager();
            return hcm;
        }
    }

    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        //ss.close();
        //ss = NewHibernateUtil.getSessionFactory().openSession();

        Query q = ss.createQuery("from modelo.Cliente");
        Iterator<Cliente> it = q.iterate();

        Cliente cliente;
        while (it.hasNext()) {
            cliente = it.next();
            listaClientes.add(cliente);
        }
        //ss.close();
        return listaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();

        if (ss.isConnected()) {
            //ss.close();
            //ss = NewHibernateUtil.getSessionFactory().openSession();
        }
        

        Query q = ss.createQuery("from modelo.Vehiculo");
        Iterator<Vehiculo> it = q.iterate();

        Vehiculo vehiculo;
        while (it.hasNext()) {
            vehiculo = it.next();
            listaVehiculos.add(vehiculo);
        }
        //ss.close();
        return listaVehiculos;
    }

    public void anadirCliente(Cliente cliente) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.persist(cliente);
        ss.getTransaction().commit();
        //ss.close();
    }

    public void anadirVehiculo(Vehiculo vehiculo) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.persist(vehiculo);
        ss.getTransaction().commit();
        //ss.close();
    }

    //Modificar
    public void anadirClienteToVehiculo(Cliente cliente, Vehiculo vehiculo) {
       // ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        vehiculo.setCliente(cliente);
        ss.getTransaction().commit();
       // ss.close();
    }

    //Modificar
    public void anadirVehiculoToCliente(Vehiculo vehiculo, Cliente cliente) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        cliente.getListaVehiculos().add(vehiculo);
        ss.getTransaction().commit();
        //ss.close();
    }

    public void modificarCliente(Cliente cliente, Cliente actualizado) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        cliente.setCiudad(actualizado.getCiudad());
        cliente.setDireccion(actualizado.getDireccion());
        //cliente.setListaVehiculos(actualizado.getListaVehiculos());
        cliente.setNombre(actualizado.getNombre());
        cliente.setNumTLF(actualizado.getNumTLF());
        
        ss.update(cliente);
        ss.getTransaction().commit();
        //ss.close();
    }

    public void modificarVehiculo(Vehiculo vehiculo, Vehiculo actualizado) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        
        ss.beginTransaction();
        
        vehiculo.setCliente(actualizado.getCliente());
        vehiculo.setColor(actualizado.getColor());
        vehiculo.setMarca(actualizado.getMarca());
        vehiculo.setModelo(actualizado.getModelo());
        vehiculo.setPrecioCompra(actualizado.getPrecioCompra());
        ss.update(vehiculo);
        ss.getTransaction().commit();
        //ss.close();
    }

    public void borrarCliente(Cliente cliente) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.delete(cliente);
        ss.getTransaction().commit();
        //ss.close();
    }

    public void borrarVehiculo(Vehiculo vehiculo) {
        //ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.delete(vehiculo);
        ss.getTransaction().commit();
        //ss.close();
    }

}
