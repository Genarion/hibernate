/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import run.HibernateConnectionManager;
import vista.JDAnadeCliente;

/**
 *
 * @author Mario
 */
public class CtrlJDAnadeCliente {

    private JDAnadeCliente vista;

    public CtrlJDAnadeCliente() {
        vista = new JDAnadeCliente(null, true);
        vista.setControlador(this);
        modeloTablaVehiculosTotales();
        modeloTablaVehiculosCliente();
        rellenaTablaVehiculosTotales();

        vista.setVisible(true);
    }

    public DefaultTableModel tableModelVehiculosTotales = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    };

    public DefaultTableModel tableModelVehiculosCliente = new DefaultTableModel() {

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
    public void modeloTablaVehiculosTotales() {
        tableModelVehiculosTotales.addColumn("Vehiculos Totales");
        vista.setjTableVehiculosTotales(tableModelVehiculosTotales);
    }

    public void modeloTablaVehiculosCliente() {
        tableModelVehiculosCliente.addColumn("Vehiculos Cliente");
        vista.setjTableVehiculosCliente(tableModelVehiculosCliente);
    }

    public void rellenaTablaVehiculosTotales() {
        for (int i = 0; i < vista.getjTableVehiculosTotales().getRowCount(); i++) {
            tableModelVehiculosTotales.removeRow(i);
            i -= 1;
        }

        try {
            Object[] datos = new Object[1];
            for (Vehiculo v : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {

                if (v.getCliente() == null) {
                    datos[0] = v.getNumBastidor();
                    tableModelVehiculosTotales.addRow(datos);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Anadir() {

        //List<Vehiculo> listaVehiculos = new ArrayList<>();
        
        try {
            ClienteDAO.getClienteDAO().anadirCliente(new Cliente(vista.getjTextFieldDNI().getText(), new ArrayList<Vehiculo>(), vista.getjTextFieldNombre().getText(), vista.getjTextFieldDireccion().getText(), Integer.parseInt(vista.getjTextFieldNumTLF().getText()), vista.getjTextFieldCiudad().getText()));
            ClienteDAO.getClienteDAO().cargarClientes();
            //Cliente cliente = new Cliente(vista.getjTextFieldDNI().getText(), new ArrayList<Vehiculo>(), vista.getjTextFieldNombre().getText(), vista.getjTextFieldDireccion().getText(), Integer.parseInt(vista.getjTextFieldNumTLF().getText()), vista.getjTextFieldCiudad().getText());
            

            JOptionPane.showMessageDialog(vista, "cliente añadido correctamente", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al añadir el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            for (int i = 0; i < tableModelVehiculosCliente.getRowCount(); i++) {
                String nBastidor = String.valueOf(tableModelVehiculosCliente.getValueAt(i, 0));

                for (Vehiculo v : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                    if (v.getNumBastidor().equals(nBastidor)) {
                        Cliente cliente = null;
                        for (Cliente t : HibernateConnectionManager.hibernateConnectionManager().getClientes()) {
                            if (t.getDni().equals(vista.getjTextFieldDNI().getText())) {
                                cliente = t;
                            }
                        }
                        VehiculoDAO.getVehiculoDAO().actualizarVehiculo(v, new Vehiculo(v.getNumBastidor(), v.getMarca(), v.getModelo(), v.getColor(), v.getPrecioCompra(), cliente));
                    }
                }

            }
            VehiculoDAO.getVehiculoDAO().cargarVehiculos();
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos para añadir el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        limpiarCampos();
    }

    public void limpiarCampos() {
        rellenaTablaVehiculosTotales();
        for (int i = 0; i < vista.getjTableVehiculosCliente().getRowCount(); i++) {
            tableModelVehiculosCliente.removeRow(i);
            i -= 1;
        }
        vista.getjTextFieldCiudad().setText("");
        vista.getjTextFieldDNI().setText("");
        vista.getjTextFieldDireccion().setText("");
        vista.getjTextFieldNombre().setText("");
        vista.getjTextFieldNumTLF().setText("");
    }

    public void asignarACliente() {
        try {
            String[] dato = new String[1];
            dato[0] = String.valueOf(tableModelVehiculosTotales.getValueAt(vista.getjTableVehiculosTotales().getSelectedRow(), 0));
            tableModelVehiculosCliente.addRow(dato);
            tableModelVehiculosTotales.removeRow(vista.getjTableVehiculosTotales().getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No ha seleccionado ningun elemento", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void quitarVehiculoCliente() {
        try {
            String[] dato = new String[1];
            dato[0] = String.valueOf(tableModelVehiculosCliente.getValueAt(vista.getjTableVehiculosCliente().getSelectedRow(), 0));
            tableModelVehiculosTotales.addRow(dato);
            tableModelVehiculosCliente.removeRow(vista.getjTableVehiculosCliente().getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No ha seleccionado ningun elemento", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
