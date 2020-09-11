/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.chatthread;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class SenderThread extends Thread{
    private static InetAddress IPAddress;
    private static DatagramSocket clientSocket;
    private static int port;

    public SenderThread(DatagramSocket Socket, InetAddress ipaddress, int port) throws SocketException {
        IPAddress = ipaddress;
        SenderThread.port = port;
        clientSocket = Socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("You > ");
            String message = scanner.nextLine();
            UDPSocket.sendMessage(clientSocket, message, IPAddress, port);
        }
    }
}
