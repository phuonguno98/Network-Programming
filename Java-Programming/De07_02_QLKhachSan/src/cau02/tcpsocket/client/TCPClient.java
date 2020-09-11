/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.khachhang.KhachHang;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPClient {

    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            //KHỞI TẠO CÁC ĐỐI TƯỢNG SỬ DỤNG
            String hostname = "localhost";
            int port = 1122;
            Socket socket = new Socket(hostname, port);

            while (true) {
                //XÁC ĐỊNH CHỨC NĂNG NGƯỜI DÙNG
                printMenu();
                int chucnang = Integer.parseInt(SCANNER.nextLine());

                //GỬI CHỨC NĂNG SANG SERVER
                TCPSocket.sendMessageObject(socket, chucnang);

                //XỬ LÝ CHỨC NĂNG
                switch (chucnang) {
                    case 1:     //THÊM KHÁCH HÀNG
                        System.out.println("<<THÊM KHÁCH HÀNG>>");

                        //GỬI THÔNG TIN KHÁCH HÀNG SANG SERVER
                        TCPSocket.sendMessageObject(socket, createKhachHang());

                        //NHẬN KẾT QUẢ XỬ LÝ TỪ SERVER
                        System.out.println(TCPSocket.receiveMessageObject(socket));
                        break;
                    case 2:     //TÍNH TIỀN PHÒNG
                        System.out.println("<<TÍNH TIỀN PHÒNG>>");

                        //GỬI THÔNG TIN CỦA PHÒNG SANG SERVER
                        System.out.print("Tên khách hàng: ");
                        TCPSocket.sendMessageObject(socket, SCANNER.nextLine());
                        System.out.print("Số ngày ở: ");
                        TCPSocket.sendMessageObject(socket, Integer.parseInt(SCANNER.nextLine()));

                        //NHẬN KẾT QUẢ TIỀN PHÒNG
                        System.out.println(TCPSocket.receiveMessageObject(socket));
                        break;
                    case 3:     //XEM DANH SÁCH KHÁCH HÀNG
                        System.out.println("<<DANH SÁCH KHÁCH HÀNG>>");
                        System.out.println(TCPSocket.receiveMessageObject(socket));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC IN MENU
    public static void printMenu() {
        System.out.println("<<DANH SÁCH CHỨC NĂNG>>");
        System.out.println("1) Thêm khách hàng");
        System.out.println("2) Tính tiền phòng");
        System.out.println("3) Xem danh sách khách hàng");
        System.out.print("Chọn chức năng > ");
    }

    //PHƯƠNG THỨC NHẬP THÔNG TIN KHÁCH HÀNG
    public static KhachHang createKhachHang() {
        KhachHang kh = new KhachHang();
        System.out.print("Mã khách hàng: ");
        kh.setMakhachhang(Integer.parseInt(SCANNER.nextLine()));
        System.out.print("Tên khách hàng: ");
        kh.setTenkhachhang(SCANNER.nextLine());
        System.out.print("Loại phòng: ");
        kh.setLoaiphong(SCANNER.nextLine());
        return kh;
    }
}
