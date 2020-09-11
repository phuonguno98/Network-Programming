/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.server;

import cau02.udpsocket.sach.Sach;
import cau02.udpsocket.message.Message;
import java.io.IOException;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.*;

/**
 *
 * @author UNO
 */
public class UDPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        UDPSocket.port = 6978;
        Message message;
        String query;
        Connection connectDatabase;
        PreparedStatement preparedStatement;
        DatagramSocket serverSocket = new DatagramSocket(UDPSocket.port);
        System.out.println("WELCOME TO SERVER");

        while (true) {

            //NHẬN DỮ LIỆU TỪ CLIENT
            message = (Message) UDPSocket.receiveObjectMessage(serverSocket);

            //XỬ LÝ DỮ LIỆU
            switch (message.getChucnang()) {
                case Message.HIEN_THI_THONG_TIN_SACH:
                    //LẤY DANH SÁCH SINH VIÊN
                    query = "SELECT * FROM SACH";
                    connectDatabase = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QUANLYSACH");
                    preparedStatement = connectDatabase.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ArrayList<Sach> listSach = new ArrayList<>();
                    while (resultSet.next()) {
                        listSach.add(new Sach(resultSet.getInt("MASACH"),
                                resultSet.getString("TENSACH"),
                                resultSet.getString("NHAXUATBAN"),
                                resultSet.getDouble("SOLUONGTONG"),
                                resultSet.getDouble("SOLUONGMUON")));
                    }

                    //GỬI KẾT QUẢ VỀ CLIENT
                    message.setListSachs(listSach);
                    UDPSocket.sendObjectMessage(serverSocket, message, UDPSocket.IPAddress, UDPSocket.port);
                    System.out.println("Xử lý xong!");
                    break;
                case Message.MUON_SACH:
                    //CẬP NHẬT CƠ SỞ DỮ LIỆU
                    query = "UPDATE SACH SET SOLUONGTONG=SOLUONGTONG-1, SOLUONGMUON=SOLUONGMUON+1 WHERE MASACH=?";
                    connectDatabase = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QUANLYSACH");
                    preparedStatement = connectDatabase.prepareStatement(query);
                    preparedStatement.setInt(1, message.getSach().getMasach());

                    //GỬI KẾT QUẢ VỀ CLIENT
                    if (preparedStatement.executeUpdate() <= 0) { //KHÔNG TÌM THẤY SÁCH/KHÔNG ĐỦ SÁCH
                        UDPSocket.sendMessage(serverSocket, "Không tìm thấy sách!", UDPSocket.IPAddress, UDPSocket.port);
                        break;
                    }

                    UDPSocket.sendMessage(serverSocket, "Mượn thành công!", UDPSocket.IPAddress, UDPSocket.port);
                    System.out.println("Đã cập nhật!");
                    break;
                case Message.TRA_SACH:
                    //CẬP NHẬT CƠ SỞ DỮ LIỆU
                    connectDatabase = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QUANLYSACH");
                    query = "UPDATE SACH SET SOLUONGTONG=SOLUONGTONG+1, SOLUONGMUON=SOLUONGMUON-1 WHERE MASACH=?";
                    preparedStatement = connectDatabase.prepareStatement(query);
                    preparedStatement.setInt(1, message.getSach().getMasach());

                    //GỬI KẾT QUẢ VỀ CLIENT
                    if (preparedStatement.executeUpdate() <= 0) {
                        UDPSocket.sendMessage(serverSocket, "Không tìm thấy sách!", UDPSocket.IPAddress, UDPSocket.port);
                        break;
                    }
                    UDPSocket.sendMessage(serverSocket, "Đã cập nhật!", UDPSocket.IPAddress, UDPSocket.port);
                    System.out.println("Đã cập nhật!");
            }
        }
    }
}
