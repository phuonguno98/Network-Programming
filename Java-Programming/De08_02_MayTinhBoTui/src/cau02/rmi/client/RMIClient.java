/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.client;
import cau02.rmi.CalculatorInterface;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author UNO
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            Remote remote = (Remote)Naming.lookup("rmi://localhost:1258/calculator");
            CalculatorInterface ref = (CalculatorInterface)remote;

            Scanner scanner = new Scanner(System.in);
            System.out.print("Số a = ");
            float a = scanner.nextInt();
            System.out.print("Số b = ");
            float b = scanner.nextInt();
            System.out.println("<<CHỌN PHÉP TÍNH>>");
            System.out.println("1) Cộng");
            System.out.println("2) Trừ");
            System.out.println("3) Nhân");
            System.out.println("4) Chia");
            System.out.print("Thực hiện phép tính > ");
            switch(scanner.nextInt()){
                case 1:
                    System.out.printf("%s + %s = %s\n", a, b, ref.add(a, b));
                    break;
                case 2:
                    System.out.printf("%s - %s = %s\n", a, b, ref.subtract(a, b));
                    break;
                case 3:
                    System.out.printf("%s x %s = %s\n", a, b, ref.multiple(a, b));
                    break;
                case 4:
                    System.out.printf("%s : %s = %s\n", a, b, ref.divide(a, b));
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
