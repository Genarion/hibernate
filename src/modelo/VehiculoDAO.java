/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import run.HibernateConnectionManager;

/**
 *
 * @author Mario
 */
public class VehiculoDAO {

    private List<Vehiculo> listaVehiculos = new ArrayList<>();
    private static VehiculoDAO miVehiculoDAO;

    public static VehiculoDAO getVehiculoDAO() {

        if (miVehiculoDAO == null) {

            miVehiculoDAO = new VehiculoDAO();
        }
        return miVehiculoDAO;
    }

    public void cargarVehiculos() throws SQLException {
        listaVehiculos.removeAll(listaVehiculos);
        listaVehiculos = HibernateConnectionManager.hibernateConnectionManager().getVehiculos();

    }

    public void anadirVehiculo(Vehiculo vehiculo) throws SQLException {
        HibernateConnectionManager.hibernateConnectionManager().anadirVehiculo(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) throws SQLException {
        HibernateConnectionManager.hibernateConnectionManager().borrarVehiculo(vehiculo);
    }

    public void actualizarVehiculo(Vehiculo vehiculo,Vehiculo actualizado) throws SQLException {
        HibernateConnectionManager.hibernateConnectionManager().modificarVehiculo(vehiculo, actualizado);
    }

    public List<Vehiculo> getListaVehiculos() throws SQLException {
        if (listaVehiculos.isEmpty()) {
            cargarVehiculos();
        }
        return listaVehiculos;
    }

    public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

}
