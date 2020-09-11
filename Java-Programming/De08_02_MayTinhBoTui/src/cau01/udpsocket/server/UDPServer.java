/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.udpsocket.server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.UDPSocket;
import static mylib.UDPSocket.port;

/**
 *
 * @author UNO
 */
public class UDPServer {
    public static void main(String[] args) {
        try {
            UDPSocket.port = 6642;
            DatagramSocket datagramSocket = new DatagramSocket(port);
            
            int number = Integer.parseInt(UDPSocket.receiveObjectMessage(datagramSocket).toString());
            
            UDPSocket.sendObjectMessage(datagramSocket, Math.abs(number), UDPSocket.IPAddress, UDPSocket.port);
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
