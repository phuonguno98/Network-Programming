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
            UDPSocket.port = 9966;
            DatagramSocket datagramSocket = new DatagramSocket(UDPSocket.port);
            System.out.println("WELCOME TO SERVER!");
            int number = Integer.parseInt(UDPSocket.receiveObjectMessage(datagramSocket).toString());
            String str;
            switch (number) {
                case 0:
                    str = "Không";
                    break;
                case 1:
                    str = "Một";
                    break;
                case 2:
                    str = "Hai";
                    break;
                case 3:
                    str = "Ba";
                    break;
                case 4:
                    str = "Bốn";
                    break;
                case 5:
                    str = "Năm";
                    break;
                case 6:
                    str = "Sáu";
                    break;
                case 7:
                    str = "Bảy";
                    break;
                case 8:
                    str = "Tám";
                    break;
                case 9:
                    str = "Chín";
                    break;
                default:
                    str = "Chỉ xử lý các số từ 0 đến 9!";
            }
            UDPSocket.sendMessage(datagramSocket, str, UDPSocket.IPAddress, UDPSocket.port);
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
