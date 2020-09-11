package cau01.tcpsocket.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
            int port = 9999;
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            
            int number = Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString());
            
            TCPSocket.sendMessageObject(socket, checkEvenNumber(number));
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //PHƯƠNG THỨC KIỂM TRA SỐ CHẴN LẼ
    public static boolean checkEvenNumber(int number) {
        return number % 2 == 0;
    }
}
