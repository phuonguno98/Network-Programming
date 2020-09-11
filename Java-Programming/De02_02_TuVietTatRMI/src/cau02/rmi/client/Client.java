/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.client;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import cau02.rmi.WordInterface;

/**
 *
 * @author UNO
 */
public class Client {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Remote remote = (Remote) Naming.lookup("rmi://localhost:6657/Word");
            WordInterface reference = (WordInterface) remote;
            String word, result;
            while (true) {
                printMenu();
                int chucnang = Integer.parseInt(scanner.nextLine());
                switch (chucnang) {
                    case 1:     //TRA TỪ VIẾT TẮT - ĐẦY ĐỦ
                        System.out.println("<<TRA TỪ VIẾT TẮT - ĐẦY ĐỦ>>");
                        System.out.print("Nhập từ viết tắt: ");
                        word = scanner.nextLine();
                        result = reference.convertAcronymToFull(word);
                        System.out.println("Từ đầy đủ là: " + result);
                        break;
                    case 2:     //TRA TỪ ĐẦY ĐỦ - VIẾT TẮT
                        System.out.println("<<TRA TỪ ĐẦY ĐỦ - VIẾT TẮT>>");
                        System.out.print("Nhập từ đầy đủ: ");
                        word = scanner.nextLine();
                        result = reference.convertFullToAcronym(word);
                        System.out.println("Từ viết tắt là: " + result);
                }
            }
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THÚC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("<<<DANH SÁCH CHỨC NĂNG>>>");
        System.out.println("1) Tra từ viết tắt - đầy đủ");
        System.out.println("2) Tra từ đầy đủ - viết tắt");
        System.out.print("Chọn chức năng > ");
    }
}
