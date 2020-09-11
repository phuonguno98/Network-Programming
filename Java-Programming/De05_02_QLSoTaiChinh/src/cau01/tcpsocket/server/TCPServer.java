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
            int port = 9987;
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            String str = TCPSocket.receiveMessageObject(socket).toString();

            int length = str.length();

            TCPSocket.sendMessageObject(socket, length);
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
