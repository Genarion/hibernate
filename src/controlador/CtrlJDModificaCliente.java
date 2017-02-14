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
import vista.JDModificaCliente;

/**
 *
 * @author Mario
 */
public class CtrlJDModificaCliente {

    private JDModificaCliente vista;
    private Cliente c;

    public CtrlJDModificaCliente(Cliente c) {
        this.vista = new JDModificaCliente(null, true);
        vista.setControlador(this);
        this.c = c;
        vista.getjTextFieldDNI().setText(c.getDni());
        vista.getjTextFieldCiudad().setText(c.getCiudad());
        vista.getjTextFieldDireccion().setText(c.getDireccion());
        vista.getjTextFieldNombre().setText(c.getNombre());
        vista.getjTextFieldNumTLF().setText(String.valueOf(c.getNumTLF()));
        modeloTablaVehiculosTotales();
        modeloTablaVehiculosCliente();
        rellenaTablaVehiculosTotales();
        rellenaTablaVehiculosCliente();

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
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void rellenaTablaVehiculosCliente() {
        for (int i = 0; i < vista.getjTableVehiculosCliente().getRowCount(); i++) {
            tableModelVehiculosCliente.removeRow(i);
            i -= 1;
        }

        try {
            Object[] datos = new Object[1];
            for (Vehiculo v : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                if (v.getCliente() == null ? c.getDni() == null : v.getCliente().getDni().equals(c.getDni())) {
                    datos[0] = v.getNumBastidor();
                    tableModelVehiculosCliente.addRow(datos);
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos del cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Modificar() {
        List<Vehiculo> listaVehiculosCliente = new ArrayList<>();
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

            for (int i = 0; i < tableModelVehiculosTotales.getRowCount(); i++) {
                String nBastidor = String.valueOf(tableModelVehiculosTotales.getValueAt(i, 0));

                for (Vehiculo v : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                    if (v.getNumBastidor().equals(nBastidor)) {
                        HibernateConnectionManager.hibernateConnectionManager().modificarVehiculo(v,new Vehiculo(v.getNumBastidor(), v.getMarca(), v.getModelo(), v.getColor(), v.getPrecioCompra(),null));
                    }
                }
                VehiculoDAO.getVehiculoDAO().cargarVehiculos();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos para modificar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {
            Cliente c1 = null;
            for (Cliente c : HibernateConnectionManager.hibernateConnectionManager().getClientes()) {
                if (c.getDni().equals(vista.getjTextFieldDNI().getText())) {
                    c1 = c;
                }
            }
            ClienteDAO.getClienteDAO().actualizarCliente(c1, new Cliente(vista.getjTextFieldDNI().getText(), new ArrayList<Vehiculo>(), vista.getjTextFieldNombre().getText(), vista.getjTextFieldDireccion().getText(), Integer.parseInt(vista.getjTextFieldNumTLF().getText()), vista.getjTextFieldCiudad().getText()));

            ClienteDAO.getClienteDAO().cargarClientes();
            JOptionPane.showMessageDialog(vista, "cliente modificado correctamente", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "error al modificar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void asignarACliente() {
        String[] dato = new String[1];
        dato[0] = String.valueOf(tableModelVehiculosTotales.getValueAt(vista.getjTableVehiculosTotales().getSelectedRow(), 0));
        tableModelVehiculosCliente.addRow(dato);
        tableModelVehiculosTotales.removeRow(vista.getjTableVehiculosTotales().getSelectedRow());
    }

    public void quitarVehiculoCliente() {
        String[] dato = new String[1];
        dato[0] = String.valueOf(tableModelVehiculosCliente.getValueAt(vista.getjTableVehiculosCliente().getSelectedRow(), 0));
        tableModelVehiculosTotales.addRow(dato);
        tableModelVehiculosCliente.removeRow(vista.getjTableVehiculosCliente().getSelectedRow());
    }
}
