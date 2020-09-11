/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpsocket.server;

import ChatThread.ReceiverThread;
import java.io.IOException;
import java.net.ServerSocket;
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
public class UDPServer {

    public static void main(String[] args) {
        try {
            int port = 7920;     //PORT ĐỂ TẠO SERVERSOCKET
            
            //KHỞI TẠO SOCKET
            ServerSocket serversocket = new ServerSocket(port);
            Socket socket = serversocket.accept();
            
            //LUỒNG CÔNG VIỆC NHẬN TIN NHẮN
            ReceiverThread receiver = new ReceiverThread(socket);
            receiver.start();

            //LUỒNG CÔNG VIỆC GỬI TIN NHẮN
            while (true) {
                System.out.print("You> ");
                String message = new Scanner(System.in).nextLine();
                TCPSocket.sendMessageObject(socket, message);
            }
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
