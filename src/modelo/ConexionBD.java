/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mario
 */
public class ConexionBD {

    private static Connection conn = null;
    private static Connection con = null;

    /**
     * Realiza la conexion con la Base de Datos
     *
     * @return Objeto de la clase Connection con la conexion establecida
     */
    public static Connection getConnection() {
        try {
            if (conn == null) {
                Runtime.getRuntime().addShutdownHook(new MiShDwHook());
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/ventavehiculos";
                String usuario = "root";
                String password = "";
                Class.forName(driver);
                conn = DriverManager.getConnection(url, usuario, password);
            }
            return conn;
        } catch (SQLException | ClassNotFoundException  ex) {
            return null;
        }

    }

    public static Connection getConnectionCreate() {
        try {
            if (con == null) {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost";
                String usuario = "root";
                String password = "";
                Class.forName(driver);
                con = DriverManager.getConnection(url, usuario, password);
            }

            return con;
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }

    }

    /*
     * Creamos un hilo para que automaticamente se cierre la conexion de la BD
     */
    static class MiShDwHook extends Thread {

        @Override
        public void run() {
            try {
                Connection conn = ConexionBD.getConnection();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException | IllegalStateException ex) {
                System.out.println("No se ha podido cerrar la conexion porque no existe.");
            }
        }
    }
}
