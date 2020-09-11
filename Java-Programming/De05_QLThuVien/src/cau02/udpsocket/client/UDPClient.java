/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.client;

import cau02.udpsocket.sach.Sach;
import cau02.udpsocket.message.Message;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import mylib.*;

/**
 *
 * @author UNO
 */
public class UDPClient {

    //TẠO PHƯƠNG THỨC NHẬP DỮ LIỆU TỪ BÀN PHÍM
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        UDPSocket.IPAddress = InetAddress.getByName("localhost");
        UDPSocket.port = 6978;
        DatagramSocket socket = new DatagramSocket();   //KHỞI TẠO DATAGRAM SOCKET

        while (true) {
            Message message = new Message();

            //IN MENU CHỨC NĂNG
            printMenu();
            message.setChucnang(Integer.parseInt(scanner.nextLine()));

            //XỬ LÝ CHỨC NĂNG
            switch (message.getChucnang()) {
                case Message.HIEN_THI_THONG_TIN_SACH:
                    System.out.println("<<<XEM THÔNG TIN SÁCH>>>");

                    //GỬI YÊU CẦU SANG SERVER
                    UDPSocket.sendObjectMessage(socket, message, UDPSocket.IPAddress, UDPSocket.port);

                    //NHẬN KẾT QUẢ VỀ TỪ SERVER
                    ArrayList<Sach> listSachs = ((Message) UDPSocket.receiveObjectMessage(socket)).getListSachs();
                    System.out.println("FROM SERVER: DANH SÁCH");
                    for (Sach listSach : listSachs) {
                        System.out.println(listSach.toString());
                    }
                    break;
                case Message.MUON_SACH:
                    System.out.println("<<<MƯỢN SÁCH>>>");

                    //NHẬP THÔNG TIN MƯỢN SÁCH
                    System.out.print("Mã số sách: ");
                    message.setSach(new Sach(Integer.parseInt(scanner.nextLine()), null, null, 0, 0));

                    //GỬI KẾT YÊU CẦU SANG SERVER
                    UDPSocket.sendObjectMessage(socket, message, UDPSocket.IPAddress, UDPSocket.port);

                    //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                    System.out.print("FROM SERVER >> ");
                    System.out.println(UDPSocket.receiveMessage(socket));
                    break;
                case Message.TRA_SACH:
                    System.out.println("<<<TRẢ SÁCH>>>");

                    //NHẬP THÔNG TIN TRẢ SÁCH
                    System.out.print("Mã số sách: ");
                    message.setSach(new Sach(Integer.parseInt(scanner.nextLine()), null, null, 0, 0));

                    //GỬI YÊU CẦU SANG SERVER
                    UDPSocket.sendObjectMessage(socket, message, UDPSocket.IPAddress, UDPSocket.port);

                    //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                    System.out.print("FROM SERVER >> ");
                    System.out.println(UDPSocket.receiveMessage(socket));
            }

        }
    }

//PHƯƠNG THỨC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("=====CHỨC NĂNG=====");
        System.out.println("1) Xem thông tin sách");
        System.out.println("2) Mượn sách");
        System.out.println("3) Trả sách");
        System.out.print("Chọn chức năng >> ");
    }
}
