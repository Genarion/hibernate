/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlJDGestionVehiculos;
import controlador.CtrlJDModificaVehiculo;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import modelo.Cliente;
import modelo.Vehiculo;
import run.HibernateConnectionManager;

/**
 *
 * @author Mario
 */
public class JDGestionaVehiculos extends javax.swing.JDialog {

    private CtrlJDGestionVehiculos controlador;

    /**
     * Creates new form JDGestionaVehiculos
     */
    public JDGestionaVehiculos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVehiculos = new javax.swing.JTable();
        jButtonModificaVehiculos = new javax.swing.JButton();
        jButtonEliminarVehiculo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableVehiculos);

        jButtonModificaVehiculos.setText("Modificar Vehiculo");
        jButtonModificaVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificaVehiculosActionPerformed(evt);
            }
        });

        jButtonEliminarVehiculo.setText("Eliminar Vehiculo");
        jButtonEliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarVehiculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonModificaVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonModificaVehiculos)
                    .addComponent(jButtonEliminarVehiculo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarVehiculoActionPerformed
        try {
            String nbastidor = jTableVehiculos.getValueAt(jTableVehiculos.getSelectedRow(), 0).toString();
            String dni;
            if (jTableVehiculos.getValueAt(jTableVehiculos.getSelectedRow(), 1).toString() != "VACIO") {
                dni = jTableVehiculos.getValueAt(jTableVehiculos.getSelectedRow(), 1).toString();
            } else {
                dni = "VACIO";
            }
            controlador.eliminaVehiculo(nbastidor, dni);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Debe selecionar algun elemento", "Error de seleccion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEliminarVehiculoActionPerformed

    private void jButtonModificaVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificaVehiculosActionPerformed
        try {
            int linea = jTableVehiculos.getSelectedRow();
            String numBastidor = jTableVehiculos.getValueAt(linea, 0).toString();
            Object dniO = jTableVehiculos.getValueAt(linea, 1);
            Cliente cliente=null;
            if (dniO != null) {
                for(Cliente c : HibernateConnectionManager.hibernateConnectionManager().getClientes()){
                    if(c.getDni().equals(dniO.toString())){
                        cliente = c;
                        break;
                    }
                }
            }
            String marca = jTableVehiculos.getValueAt(linea, 2).toString();
            String modelo = jTableVehiculos.getValueAt(linea, 3).toString();
            String color = jTableVehiculos.getValueAt(linea, 4).toString();
            Float precioCompra = Float.parseFloat(jTableVehiculos.getValueAt(linea, 5).toString());


            new CtrlJDModificaVehiculo(new Vehiculo(numBastidor, marca, modelo, color, precioCompra, cliente));
            controlador.rellenaTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe selecionar algun elemento", "Error de seleccion", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonModificaVehiculosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDGestionaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDGestionaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDGestionaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDGestionaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDGestionaVehiculos dialog = new JDGestionaVehiculos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public JTable getjTableVehiculos() {
        return jTableVehiculos;
    }

    public void setjTableVehiculos(TableModel tm) {
        this.jTableVehiculos.setModel(tm);
    }

    public JButton getjButtonModificaVehiculos() {
        return jButtonModificaVehiculos;
    }

    public void setjButtonModificaVehiculos(JButton jButtonModificaVehiculos) {
        this.jButtonModificaVehiculos = jButtonModificaVehiculos;
    }

    public JButton getjButtonEliminarVehiculo() {
        return jButtonEliminarVehiculo;
    }

    public void setjButtonEliminarVehiculo(JButton jButtonEliminarVehiculo) {
        this.jButtonEliminarVehiculo = jButtonEliminarVehiculo;
    }

    public CtrlJDGestionVehiculos getControlador() {
        return controlador;
    }

    public void setControlador(CtrlJDGestionVehiculos controlador) {
        this.controlador = controlador;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminarVehiculo;
    private javax.swing.JButton jButtonModificaVehiculos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVehiculos;
    // End of variables declaration//GEN-END:variables
}
