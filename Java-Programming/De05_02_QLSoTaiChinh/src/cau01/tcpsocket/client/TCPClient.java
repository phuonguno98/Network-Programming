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
            int port = 9987;
            String hostname = "localhost";
            Socket socket = new Socket(hostname, port);

            System.out.print("Hãy nhập vào một chuỗi: ");
            String str = new Scanner(System.in).nextLine();

            TCPSocket.sendMessageObject(socket, str);

            System.out.print("Độ dài của chuỗi là: ");
            System.out.println(TCPSocket.receiveMessageObject(socket).toString());
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
