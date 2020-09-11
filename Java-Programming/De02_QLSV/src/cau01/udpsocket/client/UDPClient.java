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
import java.util.ArrayList;
import java.util.List;
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
            UDPSocket.port = 5679;
            DatagramSocket socket = new DatagramSocket();

            List<Integer> number = new ArrayList<>();
            System.out.print("Nhập vào số nguyên dương thứ nhất: ");
            number.add(Integer.parseInt(new Scanner(System.in).nextLine()));
            System.out.print("Nhập vào số nguyên dương thứ hai: ");
            number.add(Integer.parseInt(new Scanner(System.in).nextLine()));

            UDPSocket.sendObjectMessage(socket, number, UDPSocket.IPAddress, UDPSocket.port);

            System.out.println("Kết quả từ Server: " + UDPSocket.receiveMessage(socket));
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
