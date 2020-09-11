/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.client;

import cau02.rmi.DictionaryInterface;
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
            Scanner scanner = new Scanner(System.in);
            Remote remote = (Remote) Naming.lookup("rmi://localhost:1657/Dictionary");
            DictionaryInterface reference = (DictionaryInterface) remote;
            String word, result;
            while (true) {
                printMenu();
                int chucnang = Integer.parseInt(scanner.nextLine());
                switch (chucnang) {
                    case 1:     //TRA TỪ VIỆT - ANH
                        System.out.println("<<TRA TỪ VIỆT - ANH>>");
                        System.out.print("Nhập từ tiếng Việt: ");
                        word = scanner.nextLine();
                        result = reference.searchEngBasedOnVie(word);
                        System.out.println("Trong tiếng Anh là: " + result);
                        break;
                    case 2:     //TRA TỪ ANH - VIỆT
                        System.out.println("<<TRA TỪ ANH - VIỆT>>");
                        System.out.print("Nhập từ tiếng Anh: ");
                        word = scanner.nextLine();
                        result = reference.searchVieBasedOnEng(word);
                        System.out.println("Trong tiếng Việt là: " + result);
                        break;
                    case 3:     //TRA NGHĨA TỪ TIẾNG ANH
                        System.out.println("<<TRA NGHĨA TỪ TIẾNG ANH>>");
                        System.out.print("Nhập từ tiếng Anh: ");
                        word = scanner.nextLine();
                        result = reference.meaningEngWord(word);
                        System.out.println("Nghĩa của từ là: " + result);
                }
            }
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THÚC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("<<<DANH SÁCH CHỨC NĂNG>>>");
        System.out.println("1) Tra từ Việt - Anh");
        System.out.println("2) Tra từ Anh - Việt");
        System.out.println("3) Tra nghĩa của từ tiếng Anh");
        System.out.print("Chọn chức năng > ");
    }
}
