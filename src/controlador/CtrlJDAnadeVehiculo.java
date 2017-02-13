/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import vista.JDAnadeVehiculo;

/**
 *
 * @author Mario
 */
public class CtrlJDAnadeVehiculo {

    private JDAnadeVehiculo vista;

    public CtrlJDAnadeVehiculo() {
        vista = new JDAnadeVehiculo(null, true);
        vista.setControlador(this);
        vista.setVisible(true);
    }

    public void Anadir() {
        try {
            float precio;
            if (vista.getjTextFieldPrecioAlquiler().getText().isEmpty()) {
                precio = 0;
            } else {
                precio = Float.parseFloat(vista.getjTextFieldPrecioAlquiler().getText());
            }
            VehiculoDAO.getVehiculoDAO().anadirVehiculo(new Vehiculo(vista.getjTextFieldNumBastidor().getText(), vista.getjTextFieldMarca().getText(),
                    vista.getjTextFieldModelo().getText(), vista.getjTextFieldColor().getText(), precio));
            VehiculoDAO.getVehiculoDAO().cargarVehiculos();
            JOptionPane.showMessageDialog(vista, "Vehiculo añadido correctamente", "Information", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "el vehiculo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void limpiarCampos() {
        vista.getjTextFieldColor().setText("");
        vista.getjTextFieldMarca().setText("");
        vista.getjTextFieldModelo().setText("");
        vista.getjTextFieldNumBastidor().setText("");
        vista.getjTextFieldPrecioAlquiler().setText("");
    }
}
