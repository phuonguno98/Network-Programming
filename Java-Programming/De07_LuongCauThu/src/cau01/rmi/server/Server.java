/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi.server;

import cau01.rmi.ImplementTamGiac;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

/**
 *
 * @author UNO
 */
public class Server {

    public static void main(String[] args) {
        try {
            ImplementTamGiac objCallImpl = new ImplementTamGiac();
            LocateRegistry.createRegistry(1256);
            Naming.rebind("rmi://localhost:1256/TamGiac", objCallImpl);
            System.out.println("Tạo đối tượng gọi từ xa thành công!");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
