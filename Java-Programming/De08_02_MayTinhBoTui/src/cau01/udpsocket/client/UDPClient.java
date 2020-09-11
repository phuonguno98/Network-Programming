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
    public static void main(String[] args) {
        try {
            UDPSocket.IPAddress = InetAddress.getByName("localhost");
            UDPSocket.port = 6642;
            DatagramSocket socket = new DatagramSocket();
            
            System.out.print("Nhập vào một số bất kỳ: ");
            int number = new Scanner(System.in).nextInt();
            UDPSocket.sendObjectMessage(socket, number, UDPSocket.IPAddress, UDPSocket.port);
            
            System.out.printf("Trị tuyệt đối của %s là: %s\n", number, UDPSocket.receiveObjectMessage(socket).toString());
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
