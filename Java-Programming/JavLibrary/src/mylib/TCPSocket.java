/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain methods serve exchanging data
 * 
 * @author UNO
 */
public class TCPSocket {

    /**
     * METHOD TO WRITE AN OBJECT THROUGH STREAM USE TCP PROTOCOL
     * 
     * @param socket a socket is an endpoint for communication
     *               between two machines
     * @param object object to written through stream
     */
    public static void sendMessageObject(Socket socket, Object object) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(TCPSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * METHOD TO READ AN OBJECT THROUGH STREAM USE TCP PROTOCOL
     * 
     * @param socket a socket is an endpoint for communication
     *               between two machines
     * @return obj object containing data receive from stream
     */
    public static Object receiveMessageObject(Socket socket) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object obj = inputStream.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TCPSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
