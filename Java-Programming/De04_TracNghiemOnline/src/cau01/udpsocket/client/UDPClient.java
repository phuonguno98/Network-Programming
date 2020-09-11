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
            UDPSocket.port = 5698;
            DatagramSocket soket = new DatagramSocket();
            
            System.out.print("Nhập vào một ký tự thường bất kỳ: ");
            UDPSocket.sendMessage(soket, new Scanner(System.in).nextLine(), UDPSocket.IPAddress, UDPSocket.port);
            
            System.out.println("Kết quả từ Server: " + UDPSocket.receiveMessage(soket));
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
