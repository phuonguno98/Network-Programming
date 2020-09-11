/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.tcpsocket.client;

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
            String hostname = "localhost";
            int port = 3306;
            Socket socket = new Socket(hostname, port);
            System.out.print("Nhập vào một tháng bất kỳ: ");
            int month = new Scanner(System.in).nextInt();
            TCPSocket.sendMessageObject(socket, month);

            System.out.printf("Tháng %s có %s ngày.\n", month, TCPSocket.receiveMessageObject(socket).toString());
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
