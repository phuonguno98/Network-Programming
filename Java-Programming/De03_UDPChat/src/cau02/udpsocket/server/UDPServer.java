/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.server;

import cau02.udpsocket.chatthread.ReceiverThread;
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
public class UDPServer {

    public static void main(String[] args) {
        try {
            UDPSocket.IPAddress = InetAddress.getByName("localhost");   //ĐỊA CHỈ NGƯỜI NHẬN
            final int CLIENT_PORT = 7777;     //PORT CỦA NGƯỜI NHẬN
            final int SERVER_PORT = 9999;     //PORT ĐỂ KHỞI TẠO DATAGRAMSOCKET
            
            //KHỞI TẠO DATAGRAMSOCKET
            DatagramSocket serversocket = new DatagramSocket(SERVER_PORT);
            
            //LUỒNG CÔNG VIỆC NHẬN TIN NHẮN
            ReceiverThread receiver = new ReceiverThread(serversocket);
            receiver.start();

            //LUỒNG CÔNG VIỆC GỬI TIN NHẮN
            while (true) {
                System.out.print("You> ");
                String message = new Scanner(System.in).nextLine();
                UDPSocket.sendMessage(serversocket, message, UDPSocket.IPAddress, CLIENT_PORT);
            }
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
