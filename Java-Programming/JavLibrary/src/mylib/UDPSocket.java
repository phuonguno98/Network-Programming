/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain methods serve exchanging data
 *
 * @author UNO
 */
public class UDPSocket {

    public static InetAddress IPAddress;
    public static int port;

    /**
     * METHOD TO GET DATA FROM STREAM USE UDP PROTOCOL
     *
     * @param socket a socket is an endpoint for communication between two
     * machines
     * @return object containing data received from stream
     */
    public static Object receiveObjectMessage(DatagramSocket socket) {
        try {
            byte[] incomingdata = new byte[1024];
            DatagramPacket recievePacket = new DatagramPacket(incomingdata, incomingdata.length);
            socket.receive(recievePacket);
            byte[] data = recievePacket.getData();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

            /**
             * Determine address and port of source machine
             */
            IPAddress = recievePacket.getAddress();
            port = recievePacket.getPort();

            return objInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * METHOD WRITE AN OBJECT TO STREAM USE UDP PROTOCOL
     *
     * @param socket a socket is an endpoint for communication between two
     * machines
     * @param objMessage object to write to stream
     * @param ipAddress destination address
     * @param port destination port
     */
    public static void sendObjectMessage(DatagramSocket socket, Object objMessage, InetAddress ipAddress, int port) {
        try {
            byte[] data;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objOutput = new ObjectOutputStream(outputStream);
            objOutput.writeObject(objMessage);
            data = outputStream.toByteArray();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, ipAddress, port);
            socket.send(datagramPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * METHOD WRITE A STRING MESSAGE TO STREAM USE UDP PROTOCOL
     *
     * @param socket a socket is an endpoint for communication between two
     * machines
     * @param message string message to write to stream
     * @param ipAddress destination address
     * @param port destination port
     */
    public static void sendMessage(DatagramSocket socket, String message, InetAddress ipAddress, int port) {
        try {
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, ipAddress, port);
            socket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * METHOD RECEIVE A STRING MESSAGE FROM STREAM USE UDP PROTOCOL
     *
     * @param socket a socket is an endpoint for communication between two
     * machines
     * @return a string message from stream
     */
    public static String receiveMessage(DatagramSocket socket) {
        try {
            byte[] incomingdata = new byte[1024];
            DatagramPacket recievePacket = new DatagramPacket(incomingdata, incomingdata.length);
            socket.receive(recievePacket);
            String message = new String(recievePacket.getData());
            IPAddress = recievePacket.getAddress();
            port = recievePacket.getPort();
            return message;
        } catch (IOException ex) {
            Logger.getLogger(UDPSocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
