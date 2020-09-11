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
public class UDPSever {
    public static void main(String[] args) {
        try {
            UDPSocket.port = 4978;
            DatagramSocket datagramSocket = new DatagramSocket(UDPSocket.port);
            System.out.println("WELCOME TO SERVER!");
            
            double number = Double.parseDouble(UDPSocket.receiveMessage(datagramSocket));
            number = tinhBinhPhuong(number);
            
            UDPSocket.sendMessage(datagramSocket, String.valueOf(number), UDPSocket.IPAddress, UDPSocket.port);
        } catch (SocketException ex) {
            Logger.getLogger(UDPSever.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //PHƯƠNG THỨC TÍNH BÌNH PHƯƠNG MỘT SỐ BẤT KỲ
    public static double tinhBinhPhuong(double number){
        return number*number;
    }
}
