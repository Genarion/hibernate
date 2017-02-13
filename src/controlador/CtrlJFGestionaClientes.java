/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import run.HibernateConnectionManager;
import vista.JFGestionaClientes;

/**
 *
 * @author Mario
 */
public class CtrlJFGestionaClientes {

    private JFGestionaClientes vista;
    private ClienteDAO clienteDAO;

    public CtrlJFGestionaClientes(JFGestionaClientes vista) {
        this.vista = vista;
        //Creo la BD y sus tablas
   
            this.clienteDAO = ClienteDAO.getClienteDAO();


        modeloTabla();
        rellenaTabla();
    }

    public DefaultTableModel miTableModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };

    /**
     * Metodo que crea el modelo de tabla y lo establece
     */
    public void modeloTabla() {
        miTableModel.addColumn("DNI");
        miTableModel.addColumn("Nombre");
        miTableModel.addColumn("Direccion");
        miTableModel.addColumn("Telefono");
        miTableModel.addColumn("Ciudad");
        vista.setjTableClientes(miTableModel);
        vista.getjTableClientes().setAutoCreateRowSorter(true);
    }

    public void rellenaTabla() {
        for (int i = 0; i < vista.getjTableClientes().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }

        Object[] datos = new Object[5];
        try {
            clienteDAO.cargarClientes();
            for (Cliente a : clienteDAO.getListaClientes()) {
                datos[0] = a.getDni();
                datos[1] = a.getNombre();
                datos[2] = a.getDireccion();
                datos[3] = a.getNumTLF();
                datos[4] = a.getCiudad();
                miTableModel.addRow(datos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al obtener los clientes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminaCliente(String dni, String nombre, String direccion, int numTLF, String ciudad) {

        for(Cliente c1 : HibernateConnectionManager.hibernateConnectionManager().getClientes()){
            if(c1.getDni().equals(dni)){
                HibernateConnectionManager.hibernateConnectionManager().borrarCliente(c1);
            }
        }
        try {
            for (Vehiculo v : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                if (v.getCliente() == null ? dni == null : v.getCliente().getDni().equals(dni)) {
                    VehiculoDAO.getVehiculoDAO().eliminarVehiculo(v);
                }
            }
            VehiculoDAO.getVehiculoDAO().cargarVehiculos();
            //clienteDAO.eliminarCliente(c1);
            clienteDAO.cargarClientes();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "error al eliminar al cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }

        rellenaTabla();
    }

    public void modificaCliente(Cliente c) {
        new CtrlJDModificaCliente(c);
        rellenaTabla();
    }

    public void gestionaVehiculosCliente(String dni) {
        new CtrlJDGestionaVehiculosCliente(dni);
    }
}
