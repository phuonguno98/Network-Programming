/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.udpsocket.client;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class UDPClient {
    public static void main(String[] args) throws UnknownHostException {
        try {
            UDPSocket.port = 4978;
            UDPSocket.IPAddress = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();
            
            System.out.print("Nhập vào một số: ");
            double number = Double.parseDouble(new Scanner(System.in).nextLine());
            
            UDPSocket.sendMessage(datagramSocket, String.valueOf(number), UDPSocket.IPAddress, UDPSocket.port);
            
            System.out.println("Bình phương của " + number + " = " + UDPSocket.receiveMessage(datagramSocket));
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
