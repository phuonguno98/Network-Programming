/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.server;

import cau02.rmi.MiniGameImplements;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author UNO
 */
public class Server {
    public static void main(String[] args) {
        try {
            MiniGameImplements objCallImpl = new MiniGameImplements();
            LocateRegistry.createRegistry(8967);
            Naming.rebind("rmi://localhost:8967/ChoosePrice", objCallImpl);
            System.out.println("Tạo đối tượng gọi từ xa thành công!");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
