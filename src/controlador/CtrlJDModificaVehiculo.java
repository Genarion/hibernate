/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import run.HibernateConnectionManager;
import vista.JDModificaVehiculo;

/**
 *
 * @author Mario
 */
public class CtrlJDModificaVehiculo {

    private JDModificaVehiculo vista;
    private Vehiculo vehiculo;

    public CtrlJDModificaVehiculo(Vehiculo v) {
        try {
            this.vehiculo = v;
            this.vista = new JDModificaVehiculo(null, true);
            vista.setControlador(this);

            vista.getjComboBoxDNI().removeAllItems();

            vista.getjComboBoxDNI().addItem("VACIO");
            for (Cliente c : ClienteDAO.getClienteDAO().getListaClientes()) {
                vista.getjComboBoxDNI().addItem(c.getDni());
            }

            if (v.getCliente() != null) {
                System.out.println(v.getCliente().getDni());
                vista.getjComboBoxDNI().setSelectedItem(v.getCliente().getDni());
            } else {
                System.out.println(v.getCliente());
                vista.getjComboBoxDNI().setSelectedItem("VACIO");
            }

            vista.getjTextFieldColor().setText(v.getColor());
            vista.getjTextFieldMarca().setText(v.getMarca());
            vista.getjTextFieldModelo().setText(v.getModelo());
            vista.getjTextFieldNBastidor().setText(v.getNumBastidor());
            vista.getjTextFieldPrecioCOmpra().setText(String.valueOf(v.getPrecioCompra()));

            vista.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar el dni de los clientes", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizaVehiculo() {
        try {
            String numBastidor = vista.getjTextFieldNBastidor().getText();
            String marca = vista.getjTextFieldMarca().getText();
            String modelo = vista.getjTextFieldModelo().getText();
            String color = vista.getjTextFieldColor().getText();
            Float precioCompra = Float.parseFloat(vista.getjTextFieldPrecioCOmpra().getText());
            String dni = vista.getjComboBoxDNI().getSelectedItem().toString();

            Vehiculo v1 = null;
            for (Vehiculo c : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                if (c.getNumBastidor().equals(numBastidor)) {
                    v1 = c;
                }
            }

            if (dni.equals("VACIO")) {
                System.out.println("estoy en vacio");
                dni = null;
                HibernateConnectionManager.hibernateConnectionManager().modificarVehiculo(v1, new Vehiculo(numBastidor, marca, modelo, color, precioCompra, null));
            } else {
                System.out.println("estoy en con dni");
                Cliente clientePa = null;
                for (Cliente c : HibernateConnectionManager.hibernateConnectionManager().getClientes()) {
                    if (c.getDni().equals(dni)) {
                        clientePa = c;
                    }
                }
                System.out.println("le asigno el client con dni " + clientePa.getDni());
                HibernateConnectionManager.hibernateConnectionManager().modificarVehiculo(v1, new Vehiculo(numBastidor, marca, modelo, color, precioCompra, clientePa));
                for (Vehiculo s : HibernateConnectionManager.hibernateConnectionManager().getVehiculos()) {
                    if(s.getCliente()!=null){
                        System.out.println(s.getNumBastidor()+" "+s.getCliente().getDni());
                    }else{
                        System.out.println(s.getNumBastidor()+" no tiene cliente");
                    }
                }
            }

            //limpiaCampos();
            VehiculoDAO.getVehiculoDAO().cargarVehiculos();
            JOptionPane.showMessageDialog(vista, "El vehiculo se ha modificado correctamente", "Modificado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error al modificar el vehiculo", "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiaCampos() {
        vista.getjTextFieldColor().setText("");
        vista.getjTextFieldMarca().setText("");
        vista.getjTextFieldModelo().setText("");
        vista.getjTextFieldNBastidor().setText("");
        vista.getjTextFieldPrecioCOmpra().setText("");
    }
}
