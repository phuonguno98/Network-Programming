/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.question.Question;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import mylib.TCPSocket;

/**
 *
 * @author NGUYỄN THANH PHƯƠNG
 */
public class TCPClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        String hostname = "localhost";
        int port = 6789;
        Socket examSocket = new Socket(hostname, port);
        System.out.println("Kết nối đến Server thành công!");
        Scanner scanner = new Scanner(System.in);
        int i = 1;  //SỐ THỨ TỰ CÂU HỎI

        System.out.println("<<<<HÃY CHỌN ĐÁP ÁN ĐÚNG>>>>");
        while (true) {
            //NHẬN CÂU HỎI TỪ SERVER
            Question question = (Question) TCPSocket.receiveMessageObject(examSocket);

            //HIỂN THỊ CÂU HỎI
            System.out.println("\nCâu " + i++ + ": " + question.getQuestion());
            System.out.println(question.getA());
            System.out.println(question.getB());
            System.out.println(question.getC());
            System.out.println(question.getD());
            System.out.print("-> Bạn chọn đáp án: ");
            String answer = scanner.nextLine();

            //GỬI KẾT QUẢ SANG SERVER ĐỂ KIỂM TRA
            TCPSocket.sendMessageObject(examSocket, answer);

            //NHẬN KẾT QUẢ KIỂM TRA TỪ SERVER
            String result = TCPSocket.receiveMessageObject(examSocket).toString();
            System.out.println("KẾT QUẢ: " + result);
            if (result.equalsIgnoreCase("Sai!")) {
                break;
            }
        }
        examSocket.close();
    }
}
