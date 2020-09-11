/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.message.Message;
import cau02.tcpsocket.sinhvien.SinhVien;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import mylib.*;

/**
 *
 * @author UNO
 */
public class TCPClient {

    private static final Scanner scanner = new Scanner(System.in);    //PHƯƠNG THỨC NHẬP DỮ LIỆU TỪ BÀN PHÍM
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");     //PHƯƠNG THỨC ĐỊNH DẠNG NGÀY DD/MM/YYYY

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        final String hostname = "localhost";
        final int port = 1256;
        while (true) {
            Socket managerSocket = new Socket(hostname, port);
            Message message = new Message();
            SinhVien sinhvien;

            //IN MENU CHỨC NĂNG
            printMenu();
            message.setMessage(scanner.nextLine()); //THIẾT LẬP CHỨC NĂNG

            //XỬ LÝ CÁC CHỨC NĂNG
            switch (Integer.parseInt(message.getMessage())) {
                case Message.XEM_DS_SINH_VIEN:
                    System.out.println("<<<XEM DANH SÁCH SINH VIÊN>>>");

                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(managerSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    message = (Message) TCPSocket.receiveMessageObject(managerSocket);
                    ArrayList<SinhVien> listsinhvien;  //KHỞI TẠO DANH SÁCH SINH VIÊN RỖNG
                    listsinhvien = message.getListSinhViens();  //LẤY DANH SÁCH SINH VIÊN
                    for (SinhVien sv : listsinhvien) {
                        System.out.println(sv.toString());
                    }
                    break;
                case Message.XEM_THONG_TIN_SV:
                    System.out.println("<<<XEM THÔNG TIN SINH VIÊN>>>");
                    System.out.print("Nhập mã số sinh viên: ");
                    sinhvien = new SinhVien(null, scanner.nextLine(), new Date(), null);
                    message.setSinhvien(sinhvien);   // TẠO THÔNG ĐIỆP CHỨA MÃ SINH VIÊN

                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(managerSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    System.out.println((TCPSocket.receiveMessageObject(managerSocket)).toString());
                    break;
                case Message.THEM_SINH_VIEN:
                    System.out.println("<<<THÊM SINH VIÊN>>>");

                    //NHẬP THÔNG TINH SINH VIÊN
                    sinhvien = createSinhVien();
                    message.setSinhvien(sinhvien);

                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(managerSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    System.out.println(TCPSocket.receiveMessageObject(managerSocket).toString());
                    break;
                case Message.LOC_SINH_VIEN:
                    System.out.println("<<<LỌC SINH VIÊN>>>");
                    System.out.println("1) Lọc theo năm sinh");
                    System.out.println("2) Lọc theo quê quán");
                    System.out.print("> ");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            message.setTypekeyword("Year");
                            System.out.print("Năm sinh: ");
                            sinhvien = new SinhVien(null, null, new Date(Integer.parseInt(scanner.nextLine()), 01, 01), null);
                            message.setSinhvien(sinhvien);
                            break;
                        case 2:
                            message.setTypekeyword("Hometown");
                            System.out.print("Quê quán: ");
                            sinhvien = new SinhVien(null, null, new Date(), scanner.nextLine());
                            message.setSinhvien(sinhvien);
                    }

                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(managerSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    message = (Message) TCPSocket.receiveMessageObject(managerSocket);
                    ArrayList<SinhVien> listfilledSV = message.getListSinhViens();  //LẤY DANH SÁCH SINH VIÊN
                    if (!listfilledSV.isEmpty()) {
                        for (SinhVien sv : listfilledSV) {
                            System.out.println(sv.toString());
                        }
                    } else {
                        System.out.println("Không tìm thấy sinh viên phù hợp!");
                    }
            }
        }
    }

    //PHƯƠNG THỨC IN MENU CHỨC NĂNG
    public static void printMenu() {
        System.out.println("=====MENU CHỨC NĂNG=====");
        System.out.println("1) Xem danh sách các sinh viên");
        System.out.println("2) Thêm một sinh viên");
        System.out.println("3) Xem thông tin sinh viên");
        System.out.println("4) Lọc sinh viên theo quê quán/năm sinh");
        System.out.print("> Chọn chức năng: ");
    }

    //PHƯƠNG THỨC NHẬP THÔNG TIN SINH VIÊN
    public static SinhVien createSinhVien() throws ParseException {
        SinhVien sv = new SinhVien();
        System.out.print("Họ tên: ");
        sv.setTensv(scanner.nextLine());
        System.out.print("Mã sinh viên: ");
        sv.setMasv(scanner.nextLine());
        System.out.print("Ngày sinh: ");
        sv.setNgaysinh(dateFormatter.parse(scanner.nextLine()));
        System.out.print("Quê quán: ");
        sv.setQuequan(scanner.nextLine());
        return sv;
    }
}
