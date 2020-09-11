/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi.client;

import cau01.rmi.InterfaceTamGiac;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UNO
 */
public class Client {

    public static void main(String[] args) {
        try {
            Remote remote = (Remote) Naming.lookup("rmi://localhost:1256/TamGiac");
            InterfaceTamGiac reference = (InterfaceTamGiac) remote;
            System.out.println("===KIỂM TRA 3 SỐ CÓ LẬP THÀNH MỘT TAM GIÁC KHÔNG?===");
            List<Integer> number = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                System.out.print("Nhập số thứ " + i + ": ");
                number.add(Integer.parseInt(new Scanner(System.in).nextLine()));
            }
            if (reference.checkTriangle(number.get(0), number.get(1), number.get(2))) {
                System.out.println("3 số này lập thành một tam giác!");
            } else {
                System.out.println("3 số này không lập thành một tam giác!");
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
