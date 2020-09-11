/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi.client;

import cau02.rmi.mathang.MatHang;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import cau02.rmi.InterfaceMiniGame;

/**
 *
 * @author UNO
 */
public class Client {

    public static void main(String[] args) {

        try {
            Remote remote = (Remote)Naming.lookup("rmi://localhost:8967/ChoosePrice");
            InterfaceMiniGame referrence = (InterfaceMiniGame) remote;
            System.out.println("=====CHƯƠNG TRÌNH ĐOÁN GIÁ ĐÚNG=====");

            //LẤY THÔNG TIN SẢN PHẨM
            MatHang mathang = referrence.chooseMatHangRandom();
            System.out.println("Hãy đưa ra giá đúng cho sản phẩm sau:");
            System.out.println("Sản phẩm: " + mathang.getTenhang());
            System.out.println("Bạn có 7 lần đoán giá:");
            int i = 1;  //SỐ LƯỢT ĐOÁN GIÁ
            do {
                System.out.print("Lần " + i + ": ");

                //KIỂM TRA GIÁ ĐOÁN CỦA NGƯỜI DÙNG
                int c = referrence.checkClientAnswer(Double.parseDouble(new Scanner(System.in).nextLine()), mathang);
                switch (c) {
                    case 0:
                        System.out.println("Chúc mừng bạn đã đoán đúng!");
                        i++;
                        break;
                    case 1:
                        System.out.println("Giá bạn đoán cao hơn giá của sản phẩm!");
                        i++;
                        break;
                    case -1:
                        System.out.println("Giá bạn đoán thấp hơn giá của sản phẩm!");
                        i++;
                }
                if (i > 7) {
                    System.out.println("Bạn đã hết lượt đoán!");
                    break;
                } else if (c == 0) {
                    break;
                }
            } while (i <= 7);   //CÓ 7 LẦN DỰ ĐOÁN
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}