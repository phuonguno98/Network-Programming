/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpsocket.client;

import ChatThread.ReceiverThread;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class UDPClient {

    public static void main(String args[]) {
        try {
            InetAddress IPAddress = InetAddress.getByName("localhost");   //ĐỊA CHỈ NGƯỜI NHẬN
            int port = 7920;     //PORT CỦA NGƯỜI NHẬN

            //KHỞI TẠO SOCKET
            Socket socket = new Socket(IPAddress, port);

            //LUỒNG CÔNG VIỆC NHẬN TIN NHẮN
            ReceiverThread receiver = new ReceiverThread(socket);
            receiver.start();

            //LUỒNG CÔNG VIỆN GỬI TIN NHẮN
            while (true) {
                System.out.print("You> ");
                String message = new Scanner(System.in).nextLine();
                TCPSocket.sendMessageObject(socket, message);
            }
        } catch (UnknownHostException | SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
