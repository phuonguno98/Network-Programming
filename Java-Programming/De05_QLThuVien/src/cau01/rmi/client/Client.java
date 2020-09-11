/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.rmi.client;

import cau01.rmi.StringProcessInterface;
import java.net.MalformedURLException;
import java.rmi.*;
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
            //ĐĂNG KÝ ĐỐI TƯỢNG ĐIỀU KHIỂN TỪ XA
            Remote remote = (Remote)Naming.lookup("rmi://localhost:8998/StringProcess");
            StringProcessInterface referrence = (StringProcessInterface) remote;
            
            //NHẬP DỮ LIỆU
            System.out.print("Hãy nhập vào một chuỗi: ");
            String string = new Scanner(System.in).nextLine();
            
            //GỌI ĐỐI TƯỢNG ĐIỀU KHIỂN TỪ XA
            System.out.println("Độ dài của chuỗi này là: " + referrence.lengthOfString(string) + " ký tự.");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
