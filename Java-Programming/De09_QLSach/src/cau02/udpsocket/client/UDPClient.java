/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class UDPClient {

    public static void main(String[] args) {
        try {
            //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
            Scanner scanner = new Scanner(System.in);
            UDPSocket.IPAddress = InetAddress.getByName("localhost");
            UDPSocket.port = 5665;
            DatagramSocket datagramSocket = new DatagramSocket();
            while (true) {
                //XÁC ĐỊNH CHỨC NĂNG NGƯỜI DÙNG
                printMenu();
                int chucnang = Integer.parseInt(scanner.nextLine());
                UDPSocket.sendObjectMessage(datagramSocket, chucnang, UDPSocket.IPAddress, UDPSocket.port);

                //XỬ LÝ CHỨC NĂNG
                switch (chucnang) {
                    case 1:     //XEM DANH SÁCH SÁCH
                        System.out.println("<<XEM DANH SÁCH SÁCH>>");
                        System.out.println(UDPSocket.receiveObjectMessage(datagramSocket).toString());
                        break;
                    case 2:     //MƯỢN SÁCH
                        System.out.println("<<MƯỢN SÁCH>>");

                        //GỬI THÔNG TIN MƯỢN SÁCH SANG SERVER
                        System.out.print("Tên sách: ");
                        UDPSocket.sendObjectMessage(datagramSocket, scanner.nextLine(), UDPSocket.IPAddress, UDPSocket.port);
                        System.out.print("Tên người mượn: ");
                        UDPSocket.sendObjectMessage(datagramSocket, scanner.nextLine(), UDPSocket.IPAddress, UDPSocket.port);

                        //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                        System.out.println(UDPSocket.receiveObjectMessage(datagramSocket).toString());
                }
            }
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //PHƯƠNG THỨC IN MENU
    public static void printMenu() {
        System.out.println("<<<DANH SÁCH CHỨC NĂNG>>>");
        System.out.println("1) Hiển thị sách");
        System.out.println("2) Mượn sách");
        System.out.print("Chọn chức năng > ");
    }
}
