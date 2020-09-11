/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.cauthu.CauThu;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPClient {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        int port = 8888;
        String hostname = "localhost";
        Socket socket;
        CauThu cauthu;
        int chucnang;

        while (true) {

            try {
                socket = new Socket(hostname, port);
                //CHỌN CHỨC NĂNG
                printMenu();
                chucnang = Integer.parseInt(scanner.nextLine());

                //GỬI CHỨC NĂNG SANG SERVER
                TCPSocket.sendMessageObject(socket, chucnang);

                //XỬ LÝ CHỨC NĂNG
                switch (chucnang) {
                    case 1:     //THÊM MỘT CẦU THỦ
                        System.out.println("<<THÊM MỘT CẦU THỦ>>");
                        cauthu = createCauThu();    //NHẬP THÔNG TIN MỘT CẦU THỦ
                        TCPSocket.sendMessageObject(socket, cauthu);    //GỬI DỮ LIỆU CẦU THỦ SANG SERVER

                        //NHẬN KẾT QUẢ TỪ SERVER
                        System.out.println("Server >> " + TCPSocket.receiveMessageObject(socket).toString());
                        break;
                    case 2:     //TÍNH LƯƠNG  CẦU THỦ
                        System.out.println("<<THÍNH LƯƠNG CẦU THỦ>>");

                        //GỬI MÃ CẦU THỦ CẦN TÍNH LƯƠNG SANG SERVER
                        System.out.print("Mã cầu thủ: ");
                        int macauthu = Integer.parseInt(scanner.nextLine());
                        TCPSocket.sendMessageObject(socket, macauthu);

                        //GỬI THÔNG TIN SỐ TRẬN ĐẤU CỦA CẦU THỦ SANG SERVER
                        System.out.print("Số trận đấu: ");
                        int sotran = Integer.parseInt(scanner.nextLine());
                        TCPSocket.sendMessageObject(socket, sotran);

                        //NHẬN KẾT QUẢ TỪ SERVER
                        System.out.print("Lương của cầu thủ có mã số " + macauthu + " là: ");
                        System.out.println(TCPSocket.receiveMessageObject(socket).toString());
                }
            } catch (IOException ex) {
                Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //PHƯƠNG THỨC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("<<<DANH SÁCH CHỨC NĂNG>>>");
        System.out.println("1) Thêm một cầu thủ");
        System.out.println("2) Tính lương");
        System.out.print("Chọn chức năng > ");
    }

    //PHƯƠNG THỨC NHẬP THÔNG TIN MỘT CẦU THỦ
    public static CauThu createCauThu() {
        CauThu cauthu = new CauThu();
        System.out.print("Mã cầu thủ: ");
        cauthu.setMacauthu(Integer.parseInt(scanner.nextLine()));
        System.out.print("Tên cầu thủ: ");
        cauthu.setTencauthu(scanner.nextLine());
        System.out.print("Năm sinh: ");
        cauthu.setNamsinh(Integer.parseInt(scanner.nextLine()));
        System.out.print("Vị trí chơi: ");
        cauthu.setVitri(scanner.nextLine());
        System.out.print("Lương cơ bản: ");
        cauthu.setLuong(Double.parseDouble(scanner.nextLine()));
        return cauthu;
    }
}
