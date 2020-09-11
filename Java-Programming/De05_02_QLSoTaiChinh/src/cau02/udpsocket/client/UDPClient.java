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
        Scanner scanner = new Scanner(System.in);
        try {
            //KHAI BÁO ĐỐI TƯỢNG SỬ DỤNG
            UDPSocket.IPAddress = InetAddress.getByName("localhost");
            UDPSocket.port = 7798;
            DatagramSocket datagramSocket = new DatagramSocket();
            long amount;

            while (true) {
                //XÁC ĐỊNH CHỨC NĂNG NGƯỜI DÙNG
                printMenu();
                int function = Integer.parseInt(scanner.nextLine());

                //GỬI CHỨC NĂNG SANG SERVER
                UDPSocket.sendObjectMessage(datagramSocket, function, UDPSocket.IPAddress, UDPSocket.port);

                //XỬ LÝ CHỨC NĂNG
                switch (function) {
                    case 1:    //THU TIỀN
                        System.out.println("<<THU TIỀN>>");
                        System.out.print("Số tiền thu vào: ");
                        amount = Long.parseLong(scanner.nextLine());

                        //GỬI SỐ TIỀN SANG SERVER
                        UDPSocket.sendObjectMessage(datagramSocket, amount, UDPSocket.IPAddress, UDPSocket.port);

                        //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                        System.out.println(UDPSocket.receiveMessage(datagramSocket));
                        break;
                    case 2:    //CHI TIỀN
                        System.out.println("<<CHI TIỀN>>");
                        System.out.print("Số tiền chi: ");
                        amount = Long.parseLong(scanner.nextLine());

                        //GỬI SỐ TIỀN SANG SERVER
                        UDPSocket.sendObjectMessage(datagramSocket, amount, UDPSocket.IPAddress, UDPSocket.port);

                        //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                        System.out.println(UDPSocket.receiveMessage(datagramSocket));
                        break;
                    case 3:    //XEM SỐ DƯ
                        System.out.print("Số dư của tài khoản tại thời điểm truy vấn: ");

                        //NHẬN SỐ DƯ VỀ TỪ SERVER
                        System.out.println(UDPSocket.receiveMessage(datagramSocket));
                        break;
                    case 4:    //XEM LỊCH SỬ THU CHI
                        System.out.println("\t\t\t<<LỊCH SỬ THU/CHI>>");

                        //LẤY KẾT QUẢ TỪ SERVER
                        System.out.println(UDPSocket.receiveMessage(datagramSocket));
                }
            }
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("<<<DANH SÁCH CHỨC NĂNG>>>");
        System.out.println("1) Thu tiền");
        System.out.println("2) Chi tiền");
        System.out.println("3) Xem số dư");
        System.out.println("4) Xem lịch sử thu/chi");
        System.out.print("Chọn chức năng > ");
    }
}
