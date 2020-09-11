/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.client;

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
public class UDPClient {

    public static void main(String args[]) {
        /**
         * GIẢ SỬ CLIENT LÀ BÊN YÊU CÂU KẾT NỐI TRƯỚC ĐỂ GỬI TIN NHẮN VÌ SỬ DỤNG
         * GIAO THỨC UDP
         */
        try {
            UDPSocket.IPAddress = InetAddress.getByName("localhost");   //ĐỊA CHỈ NGƯỜI NHẬN
            final int SERVER_PORT = 9999;     //PORT CỦA NGƯỜI NHẬN
            final int CLIENT_PORT = 7777;     //PORT ĐỂ KHỞI TẠO DATAGRAMSOCKET

            //KHỞI TẠO DATAGRAMSOCKET
            DatagramSocket socket = new DatagramSocket(CLIENT_PORT);

            //LUỒNG CÔNG VIỆC NHẬN TIN NHẮN
            ReceiverThread receiver = new ReceiverThread(socket);
            receiver.start();

            //LUỒNG CÔNG VIỆN GỬI TIN NHẮN
            while (true) {
                System.out.print("You> ");
                String message = new Scanner(System.in).nextLine();
                UDPSocket.sendMessage(socket, message, UDPSocket.IPAddress, SERVER_PORT);
            }

        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
