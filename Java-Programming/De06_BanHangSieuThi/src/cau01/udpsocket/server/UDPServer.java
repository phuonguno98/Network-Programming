/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau01.udpsocket.server;

import java.net.DatagramSocket;
import java.net.SocketException;
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
            UDPSocket.port = 1236;
            DatagramSocket socket = new DatagramSocket(UDPSocket.port);
            System.out.print("WELCOME TO SERVER!");
            
            int number = Integer.parseInt(UDPSocket.receiveMessage(socket), 10);
            String result;
            switch (number) {
                case 1:
                    result = "Một";
                    break;
                case 2:
                    result = "Hai";
                    break;
                case 3:
                    result = "Ba";
                    break;
                case 4:
                    result = "Bốn";
                    break;
                case 5:
                    result = "Năm";
                    break;
                case 6:
                    result = "Sáu";
                    break;
                case 7:
                    result = "Bảy";
                    break;
                case 8:
                    result = "Tám";
                    break;
                case 9:
                    result = "Chín";
                    break;
                case 0:
                    result = "Không";
                    break;
                default:
                    result = "Bạn phải nhập một số từ 0 đến 9!";
            }

            UDPSocket.sendMessage(socket, result, UDPSocket.IPAddress, UDPSocket.port);
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
