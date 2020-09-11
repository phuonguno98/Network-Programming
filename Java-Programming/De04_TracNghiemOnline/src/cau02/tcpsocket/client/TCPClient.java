/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.client;

import cau02.tcpsocket.message.Message;
import cau02.tcpsocket.question.Question;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        int port = 6789;    //SỐ HIỆU CỔNG ĐỂ TẠO SOCKET
        Socket examSocket = new Socket("localhost", port);
        Message mess;   //LỚP Message CHỨA NỘI DUNG TRAO ĐỔI GIỮA CLIENT VÀ SERVER
        ArrayList<Question> questions;  //CẤU TRÚC ĐỂ LƯU DANH SÁCH CÂU HỎI
        ArrayList<String> answer = new ArrayList<>(4);  //CẤU TRÚC LƯU ĐÁP ÁN TRẢ LỜI TỪ CLIENT (3 CÂU HỎI - 3 ĐÁP ÁN)
        Scanner scanner = new Scanner(System.in);   //ĐỐI TƯỢNG NHẬN DỮ LIỆU TỪ BÀN PHÍM
                
        //NHẬN CÂU HỎI TỪ SERVER
        mess = (Message)TCPSocket.receiveMessageObject(examSocket);
        questions = mess.getQuestions();
        
        //HIỂN THỊ LẦN LƯỢT CÁC CÂU HỎI
        System.out.println("<<<<HÃY CHỌN ĐÁP ÁN ĐÚNG>>>>");
        int i = 1;  //SỐ THỨ TỰ CÂU HỎI
        for (Question question : questions) {
            System.out.println("Câu " + i++ + ": " + question.getQuestion());
            System.out.println(question.getA());
            System.out.println(question.getB());
            System.out.println(question.getC());
            System.out.println(question.getD());
            System.out.print("-> Bạn chọn đáp án: ");
            answer.add(scanner.nextLine());
        }
        
        //GỬI KẾT QUẢ SANG SERVER ĐỂ KIỂM TRA
        mess.setAnswer(answer); //GÁN DANH SÁCH KẾT QUẢ VÀO Message
        TCPSocket.sendMessageObject(examSocket, mess);
        
        //NHẬN KẾT QUẢ CUỐI CÙNG
        System.out.println("\nKẾT QUẢ: " + TCPSocket.receiveMessageObject(examSocket).toString());
    }
}
