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
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import vista.JDGestionaVehiculos;

/**
 *
 * @author Mario
 */
public class CtrlJDGestionaVehiculosCliente {

    private JDGestionaVehiculos vista;
    private String dni;
    private VehiculoDAO vehiculoDAO = VehiculoDAO.getVehiculoDAO();

    public CtrlJDGestionaVehiculosCliente(String dni) {
        this.dni = dni;
        this.vista = new JDGestionaVehiculos(null, true);
        modeloTabla();
        rellenaTabla();
        vista.getjButtonModificaVehiculos().setVisible(false);
        vista.getjButtonEliminarVehiculo().setVisible(false);
        vista.setResizable(false);
        vista.setVisible(true);
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

        Object[] datos = new Object[5];
        try {
            vehiculoDAO.cargarVehiculos();
            for (Vehiculo v : vehiculoDAO.getListaVehiculos()) {
                if (v.getCliente() != null) {
                    if (v.getCliente().getDni().equals(this.dni)) {
                        datos[0] = v.getNumBastidor();
                        datos[1] = v.getMarca();
                        datos[2] = v.getModelo();
                        datos[3] = v.getColor();
                        datos[4] = v.getPrecioCompra();
                        miTableModel.addRow(datos);
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "error al cargar los vehiculos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

//    public void eliminaVehiculo(String numBastidor, String marca, String modelo, String color, float precioAlquiler) {
//        Vehiculo v1 = new Vehiculo(numBastidor, marca, modelo, color, precioAlquiler, new String("-1"));
//        try {
//            vehiculoDAO.eliminarVehiculo(v1);
//            ClienteDAO.getClienteDAO().eliminarCliente(new Cliente(this.dni, null, null, null, -1, null));
//            vehiculoDAO.cargarVehiculos();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(vista, "error al eliminar vehiculo y su cliente", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        rellenaTabla();
//    }
}
