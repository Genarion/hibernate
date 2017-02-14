
import java.util.List;
import modelo.Vehiculo;
import run.HibernateConnectionManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author genarion
 */
public class test {
    public static void main(String[] args) {
        List<Vehiculo> lista = HibernateConnectionManager.hibernateConnectionManager().getVehiculos();
        //System.out.println(lista.get(0).getCliente().getDni());
    }
}
