/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.tcpsocket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPServer {

    public static void main(String[] args) {
        try {
            int port = 8899;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("WELCOME TO SERVER!");
            Socket socket = serverSocket.accept();
            int number = Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString());
            TCPSocket.sendMessageObject(socket, giaiThua(number));
            System.out.println("Xử lý xong!");
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC TÍNH GIAI THỪA
    public static int giaiThua(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * (giaiThua(n - 1));
    }
}
