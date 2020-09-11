/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.mathang.MatHang;
import cau02.tcpsocket.message.Message;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG CẦN DÙNG
        int port = 3698;
        String hostname = "localhost";
        Socket marketSocket;
        Scanner scanner = new Scanner(System.in);    //ĐỐI TƯỢNG NHẬP DỮ LIỆU TỪ NGƯỜI DÙNG
        Message message = new Message();

        while (true) {
            marketSocket = new Socket(hostname, port);
            //HIỂN THỊ MENU
            printMenu();
            message.setMessage(scanner.nextLine()); //XÁC ĐỊNH CHỨC NĂNG

            //XỬ LÝ CHỨC NĂNG
            switch (Integer.parseInt(message.getMessage())) {
                case Message.TIM_KIEM:
                    System.out.println("<TÌM KIẾM MẶT HÀNG THEO MÃ>");
                    System.out.print("Mã mặt hàng: ");
                    message.setMathang(new MatHang(Integer.parseInt(scanner.nextLine()), null, 0, 0));
                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(marketSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    message = (Message) TCPSocket.receiveMessageObject(marketSocket);

                    //HIỂN THỊ KẾT QUẢ                
                    System.out.println("<<<THÔNG TIN MẶT HÀNG>>>");
                    System.out.println(MatHang.INFO_PRODUCT_FORMAT + "\n" + String.format("%7s %25s %10s %15s", message.getMathang().getMahang(),
                            message.getMathang().getTenhang(),
                            message.getMathang().getSoluongtonkho(),
                            message.getMathang().getGiaban()));
                    break;
                case Message.LAP_HOA_DON:
                    System.out.println("<LẬP HÓA ĐƠN>");
                    String execute;
                    Map<Integer, Integer> mapMatHang = new HashMap<>();
                    do {
                        System.out.print("Mã hàng: ");
                        int key = Integer.parseInt(scanner.nextLine());
                        System.out.print("Số lượng: ");
                        int value = Integer.parseInt(scanner.nextLine());
                        mapMatHang.put(key, value);
                        System.out.println("Gõ phím [L]/[T] để lập hóa đơn/nhập tiếp!");
                        execute = scanner.nextLine();
                    } while (execute.equalsIgnoreCase("T"));
                    message.setMapMatHang(mapMatHang);

                    //GỬI YÊU CẦU SANG SERVER
                    TCPSocket.sendMessageObject(marketSocket, message);

                    //NHẬN KẾT QUẢ TỪ SERVER
                    message = (Message) TCPSocket.receiveMessageObject(marketSocket);
                    System.out.println(message.getMessage());
            }
        }
    }
    
    //PHƯƠNG THỨC TẠO MENU
    public static void printMenu(){
        System.out.println("<<<<<DANH SÁCH CHỨC NĂNG>>>>>");
        System.out.println("1) Tìm kiếm mặt hàng theo mã hàng.");
        System.out.println("2) Lập hóa đơn thanh toán.");
        System.out.print(">>Chọn chức năng: ");
    }
}
