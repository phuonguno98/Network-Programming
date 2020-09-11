/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import mylib.SQLServer;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class UDPServer {

    public static void main(String[] args) {
        try {
            UDPSocket.port = 5665;
            DatagramSocket datagramSocket = new DatagramSocket(UDPSocket.port);

            while (true) {
                //XÁC ĐỊNH CHỨC NĂNG NGƯỜI DÙNG
                int chucnang = Integer.parseInt(UDPSocket.receiveObjectMessage(datagramSocket).toString());
                switch (chucnang) {
                    case 1:     //XEM DANH SÁCH SÁCH
                        UDPSocket.sendObjectMessage(datagramSocket, createListBooks(), UDPSocket.IPAddress, UDPSocket.port);
                        break;
                    case 2:     //MƯỢN SÁCH
                        //XÁC ĐỊNH THÔNG TIN MƯỢN SÁCH
                        String tensach = UDPSocket.receiveObjectMessage(datagramSocket).toString();
                        String tennguoimuon = UDPSocket.receiveObjectMessage(datagramSocket).toString();

                        //CẬP NHẬT CƠ SỞ DỮ LIỆU
                        Connection conn = SQLServer.getConnection(5549, "sa", "Phuonguno", "QLSACH");
                        String query = "IF((SELECT TENNGUOIMUON FROM SACH WHERE TENSACH = N'" + tensach + "') LIKE N'CHUA MUON')\n"
                                + "BEGIN UPDATE SACH SET TENNGUOIMUON = N'" + tennguoimuon + "' WHERE TENSACH = N'" + tensach + "' END";
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        int result = preparedStatement.executeUpdate();
                        if (result > 0) {
                            UDPSocket.sendObjectMessage(datagramSocket, "Mượn thành công!", UDPSocket.IPAddress, UDPSocket.port);
                        } else {
                            UDPSocket.sendObjectMessage(datagramSocket, "Không thể mươn sách này!", UDPSocket.IPAddress, UDPSocket.port);
                        }
                }
            }
        } catch (SocketException | SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC XEM DANH SÁCH SÁCH
    public static String createListBooks() {
        String listbooks = String.format("%10s%30s%30s\n", "STT", "Tên sách", "Người mượn");
        try {
            Connection conn = SQLServer.getConnection(5549, "sa", "Phuonguno", "QLSACH");
            ResultSet resultSet = SQLServer.getResultSet(conn, "SELECT * FROM SACH");
            while (resultSet.next()) {
                listbooks = listbooks + String.format("%10s%30s%30s\n", resultSet.getInt("STT"),
                        resultSet.getString("TENSACH"),
                        resultSet.getString("TENNGUOIMUON"));
            }
            return listbooks;
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return listbooks;
        }
    }
}
