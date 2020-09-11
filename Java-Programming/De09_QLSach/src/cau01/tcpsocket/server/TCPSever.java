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
public class TCPSever {

    public static void main(String[] args) {
        try {
            int port = 3306;
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            int month = Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString());
            int date;
            switch (month) {
                case 2:
                    date = 28;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                case 10:
                case 12:
                    date = 31;
                    break;
                default:
                    date = 30;
                    break;
            }
            TCPSocket.sendMessageObject(socket, date);
        } catch (IOException ex) {
            Logger.getLogger(TCPSever.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
