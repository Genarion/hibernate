/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import run.HibernateConnectionManager;

/**
 *
 * @author Mario
 */
public class ClienteDAO {

    private List<Cliente> listaClientes = new ArrayList<>();
    private static ClienteDAO miclientedao;

    public static ClienteDAO getClienteDAO() {

        if (miclientedao == null) {

            miclientedao = new ClienteDAO();
        }
        return miclientedao;
    }

    public void cargarClientes() throws SQLException {
        listaClientes.removeAll(listaClientes);
        
            listaClientes = HibernateConnectionManager.hibernateConnectionManager().getClientes();
        
        
    }

    public void anadirCliente(Cliente client) throws SQLException {
        HibernateConnectionManager.hibernateConnectionManager().anadirCliente(client);
    }

    public void eliminarCliente(Cliente client) throws SQLException {
        HibernateConnectionManager.hibernateConnectionManager().borrarCliente(client);
    }

    public void actualizarCliente(Cliente client ,Cliente actualizado) throws SQLException {
        //System.out.println(client.getCiudad()+" "+actualizado.getCiudad());
        HibernateConnectionManager.hibernateConnectionManager().modificarCliente(client, actualizado);
    }

    public List<Cliente> getListaClientes() throws SQLException {
        if (listaClientes.isEmpty()) {
            cargarClientes();
        }
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
