/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatThread;

import java.net.Socket;
import java.net.SocketException;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class ReceiverThread extends Thread{
    private static Socket clientSocket;

    public ReceiverThread(Socket socket) throws SocketException {
        clientSocket = socket;
    }

    @Override
    public void run() {
        while (true) {
            String message = (String) TCPSocket.receiveMessageObject(clientSocket);
            System.out.println("\nYour friend> " + message);
        }
    }
}
