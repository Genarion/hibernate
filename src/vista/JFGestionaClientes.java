/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlJDAnadeCliente;
import controlador.CtrlJDAnadeVehiculo;
import controlador.CtrlJDGestionVehiculos;
import controlador.CtrlJFGestionaClientes;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Vehiculo;

/**
 *
 * @author Mario
 */
public class JFGestionaClientes extends javax.swing.JFrame {

    private CtrlJFGestionaClientes controlador;

    /**
     * Creates new form JFGestionaArchivo
     */
    public JFGestionaClientes() {
        initComponents();
        controlador = new CtrlJFGestionaClientes(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonVerVehiculos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonModificarCliente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuAnadir = new javax.swing.JMenu();
        jMenuItemAnadirCliente = new javax.swing.JMenuItem();
        jMenuItemAnadirVehivulo = new javax.swing.JMenuItem();
        jMenuVer = new javax.swing.JMenu();
        jMenuItemVerVehiculos = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableClientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jButtonVerVehiculos.setText("Ver Vehiculos Comprados del Cliente");
        jButtonVerVehiculos.setToolTipText("");
        jButtonVerVehiculos.setActionCommand("Ver Vehiculos Alquilados del Cliente (Doble click para eliminar Clientes/Vehiculos)");
        jButtonVerVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerVehiculosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Doble click para eliminar clientes/vehiculos");

        jButtonModificarCliente.setText("Modificar Cliente");
        jButtonModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarClienteActionPerformed(evt);
            }
        });

        jMenuArchivo.setText("Archivo");

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBar1.add(jMenuArchivo);

        jMenuAnadir.setText("Añadir");

        jMenuItemAnadirCliente.setText("Cliente");
        jMenuItemAnadirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnadirClienteActionPerformed(evt);
            }
        });
        jMenuAnadir.add(jMenuItemAnadirCliente);

        jMenuItemAnadirVehivulo.setText("Vehiculo");
        jMenuItemAnadirVehivulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnadirVehivuloActionPerformed(evt);
            }
        });
        jMenuAnadir.add(jMenuItemAnadirVehivulo);

        jMenuBar1.add(jMenuAnadir);

        jMenuVer.setText("Ver");

        jMenuItemVerVehiculos.setText("Vehiculos");
        jMenuItemVerVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVerVehiculosActionPerformed(evt);
            }
        });
        jMenuVer.add(jMenuItemVerVehiculos);

        jMenuBar1.add(jMenuVer);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVerVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerVehiculos)
                    .addComponent(jButtonModificarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMousePressed
        if (evt.getClickCount() == 2) {

            int lineaSeleccionada = jTableClientes.getSelectedRow();
            //variables del objeto cliente
            String dni = (String) jTableClientes.getValueAt(lineaSeleccionada,
                    0);
            String nombre = (String) jTableClientes.getValueAt(
                    lineaSeleccionada, 1);
            String direccion = (String) jTableClientes.getValueAt(
                    lineaSeleccionada, 2);
            int telefono = (Integer) jTableClientes.getValueAt(
                    lineaSeleccionada, 3);
            String ciudad = (String) jTableClientes.getValueAt(
                    lineaSeleccionada, 4);

            //0 para si y 1 para no
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "Seguro que quieres eliminar al cliente " + nombre
                    + " con dni " + dni, "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                controlador.eliminaCliente(dni, nombre, direccion, telefono, ciudad);
            }

        }
    }//GEN-LAST:event_jTableClientesMousePressed

    private void jMenuItemAnadirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnadirClienteActionPerformed
        new CtrlJDAnadeCliente();
        controlador.rellenaTabla();
    }//GEN-LAST:event_jMenuItemAnadirClienteActionPerformed

    private void jMenuItemAnadirVehivuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnadirVehivuloActionPerformed
        new CtrlJDAnadeVehiculo();
    }//GEN-LAST:event_jMenuItemAnadirVehivuloActionPerformed

    private void jButtonVerVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerVehiculosActionPerformed
        try {
            controlador.gestionaVehiculosCliente(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0).toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Debe selecionar algun elemento", "Error de seleccion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVerVehiculosActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jButtonModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarClienteActionPerformed
        try {
            int fila = jTableClientes.getSelectedRow();
            String dni = jTableClientes.getValueAt(fila, 0).toString();
            String nombre = jTableClientes.getValueAt(fila, 1).toString();
            String direccion = jTableClientes.getValueAt(fila, 2).toString();
            int numTLF = Integer.parseInt(jTableClientes.getValueAt(fila, 3).toString());
            String ciudad = jTableClientes.getValueAt(fila, 4).toString();
            Cliente c1 = new Cliente(dni, new ArrayList<Vehiculo>(), nombre, direccion, numTLF, ciudad);
            controlador.modificaCliente(c1);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Debe selecionar algun elemento", "Error de seleccion", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonModificarClienteActionPerformed

    private void jMenuItemVerVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVerVehiculosActionPerformed
        new CtrlJDGestionVehiculos();
        controlador.rellenaTabla();
    }//GEN-LAST:event_jMenuItemVerVehiculosActionPerformed

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
            java.util.logging.Logger.getLogger(JFGestionaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFGestionaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFGestionaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFGestionaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFGestionaClientes().setVisible(true);
            }
        });
    }

    public JTable getjTableClientes() {
        return jTableClientes;
    }

    public void setjTableClientes(TableModel tm) {
        this.jTableClientes.setModel(tm);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonModificarCliente;
    private javax.swing.JButton jButtonVerVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenuAnadir;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemAnadirCliente;
    private javax.swing.JMenuItem jMenuItemAnadirVehivulo;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemVerVehiculos;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}