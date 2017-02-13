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
import vista.JDGestionaVehiculos;

/**
 *
 * @author Mario
 */
public class CtrlJDGestionVehiculos {

    private JDGestionaVehiculos vista;
    private VehiculoDAO vehiculoDAO = VehiculoDAO.getVehiculoDAO();

    public CtrlJDGestionVehiculos() {
        this.vista = new JDGestionaVehiculos(null, true);
        vista.setControlador(this);
        modeloTabla();
        rellenaTabla();
        vista.getjButtonModificaVehiculos().setVisible(true);
        vista.getjButtonEliminarVehiculo().setVisible(true);
        vista.setVisible(true);
        vista.setResizable(false);
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
        miTableModel.addColumn("NºBastidor");
        miTableModel.addColumn("DNI Cliente");
        miTableModel.addColumn("Marca");
        miTableModel.addColumn("Modelo");
        miTableModel.addColumn("Color");
        miTableModel.addColumn("Precio Compra");
        vista.setjTableVehiculos(miTableModel);
        vista.getjTableVehiculos().setAutoCreateRowSorter(true);
    }

    /**
     * Metodo para rellenar la tabla con los datos de cliente de la base de
     * datos
     */
    public void rellenaTabla() {
        ArrayList<String> listaBastidores = new ArrayList<>();
        for (int i = 0; i < vista.getjTableVehiculos().getRowCount(); i++) {
            miTableModel.removeRow(i);
            i -= 1;
        }

        Object[] datos = new Object[6];
        try {
            vehiculoDAO.cargarVehiculos();
            for (Vehiculo v : vehiculoDAO.getListaVehiculos()) {

                datos[0] = v.getNumBastidor();
                if(v.getCliente() != null)
                    datos[1] = v.getCliente().getDni();
                else
                datos[1] = null;
                if (datos[1] == null) {
                    datos[1] = "VACIO";
                }
                datos[2] = v.getMarca();
                datos[3] = v.getModelo();
                datos[4] = v.getColor();
                datos[5] = v.getPrecioCompra();
                miTableModel.addRow(datos);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminaVehiculo(String numBastidor, String dni) {
        Vehiculo vehiculo = null;
        for(Vehiculo t : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()){
            if(t.getNumBastidor().equals(numBastidor)){
                vehiculo = t;
                break;
            }
        }
        try {
            vehiculoDAO.eliminarVehiculo(vehiculo);
            if (!"VACIO".equals(dni)) {
                ClienteDAO.getClienteDAO().eliminarCliente(new Cliente(dni, null, null, null, -1, null));
            }
            vehiculoDAO.cargarVehiculos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al eliminar vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        rellenaTabla();
    }
}
