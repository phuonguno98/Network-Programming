/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import cau02.tcpsocket.cauthu.CauThu;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import mylib.TCPSocket;
import mylib.SQLServer;

/**
 *
 * @author UNO
 */
public class TCPServer {

    private static final Locale localeVN = new Locale("vi", "VN");
    private static final NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public static void main(String[] args) {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            //KHAI BÁO CÁC ĐỐI TƯỢNG XỬ DỤNG
            final int port = 8888;
            ServerSocket serverSocket = new ServerSocket(port);
            CauThu cauthu;

            //XỬ LÝ
            while (true) {
                Socket socket = serverSocket.accept();

                //XÁC ĐỊNH CHỨC NĂNG CẦN XỬ LÝ
                int chucnang = Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString());
                switch (chucnang) {
                    case 1: //THÊM CẦU THỦ
                        cauthu = (CauThu) TCPSocket.receiveMessageObject(socket);
                        if (themCauThu(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLCAUTHU"), cauthu) > 0) {
                            TCPSocket.sendMessageObject(socket, "Đã thêm thành công!");
                        } else {
                            TCPSocket.sendMessageObject(socket, "Đã xảy ra lỗi!");
                        }
                        break;
                    case 2: //TÍNH TỔNG LƯƠNG THÁNG
                        cauthu = new CauThu(Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString()), null, 0, null, 0);
                        int sotran = Integer.parseInt(String.valueOf(TCPSocket.receiveMessageObject(socket)));
                        TCPSocket.sendMessageObject(socket, currencyVN.format(tongLuongThang(cauthu, sotran)));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int themCauThu(Connection connection, CauThu cauthu) {
        try {
            String query = "INSERT INTO CAUTHU(MASO, TEN, NAMSINH, VITRI, LUONG) VALUES(?, ?, ? ,?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cauthu.getMacauthu());
            preparedStatement.setString(2, cauthu.getTencauthu());
            preparedStatement.setInt(3, cauthu.getNamsinh());
            preparedStatement.setString(4, cauthu.getVitri());
            preparedStatement.setDouble(5, cauthu.getLuong());
            return preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static double tongLuongThang(CauThu cauthu, int sotran) {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            String query = "SELECT * FROM CAUTHU WHERE MASO = " + cauthu.getMacauthu();
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLCAUTHU");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cauthu = new CauThu(0, null, 0, resultSet.getString("VITRI"), resultSet.getDouble("LUONG"));
            } else {
                return 0;
            }
            double hesothuong;
            switch (cauthu.getVitri()) {
                case "Tien Dao":
                    hesothuong = 0.025;
                    break;
                case "Thu Mon":
                    hesothuong = 0.015;
                    break;
                default:
                    hesothuong = 0.02;
            }
            return cauthu.getLuong() * (1 + sotran * hesothuong);
        } catch (SQLException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
