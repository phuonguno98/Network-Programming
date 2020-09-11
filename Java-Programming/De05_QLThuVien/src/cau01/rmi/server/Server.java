/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi.server;

import cau01.rmi.StringProcessImpliment;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UNO
 */
public class Server {

    public static void main(String args[]) {
        try {
            //TẠO ĐỐI TƯỢNG CHO PHÉP ĐIỀU KHIỂN TỪ XA
            StringProcessImpliment objCallImpl = new StringProcessImpliment();
            LocateRegistry.createRegistry(8998);
            Naming.rebind("rmi://localhost:8998/StringProcess", objCallImpl);
            
            System.out.println("Tạo đối tượng gọi từ xa thành công!");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
