/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.chatthread;

import java.net.DatagramSocket;
import java.net.SocketException;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class ReceiverThread extends Thread{
    private static DatagramSocket ClientSocket;

    public ReceiverThread(DatagramSocket socket) throws SocketException {
        ClientSocket = socket;
    }

    @Override
    public void run() {
        while (true) {
            String message = UDPSocket.receiveMessage(ClientSocket);
            System.out.println("\nYour friend> " + message);
        }
    }
}
