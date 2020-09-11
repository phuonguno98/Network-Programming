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

/**
 *
 * @author UNO
 */
public class UDPServer {

    public static void main(String[] args) {
        try {
            UDPSocket.port = 5698;
            DatagramSocket soket = new DatagramSocket(UDPSocket.port);
            System.out.print("WELCOME TO SERVER!");

            String character = UDPSocket.receiveMessage(soket);

            character = character.toUpperCase();

            UDPSocket.sendMessage(soket, character, UDPSocket.IPAddress, UDPSocket.port);
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
