package cau01.tcpsocket.client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

    public static void main(String[] args) {
        try {
            int port = 9999;
            String hostname = "localhost";
            Socket socket = new Socket(hostname, port);
            System.out.println("<<<KIỂM TRA SỐ CHẴN>>>");

            System.out.print("Nhập vào một số tự nhiên bất kỳ: ");
            int number = new Scanner(System.in).nextInt();

            TCPSocket.sendMessageObject(socket, number);

            System.out.println("Kết quả kiểm tra: " + TCPSocket.receiveMessageObject(socket));
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
