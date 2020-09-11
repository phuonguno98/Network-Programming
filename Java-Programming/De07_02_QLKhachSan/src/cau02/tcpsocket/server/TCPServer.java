/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import cau02.tcpsocket.khachhang.KhachHang;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import mylib.SQLServer;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPServer {

    public static void main(String[] args) {
        try {
            int port = 1122;
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            while (true) {
                switch (Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString())) {
                    case 1:     //THÊM KHÁCH HÀNG
                        //NHẬN THÔNG TIN CỦA KHÁCH HÀNG TỪ CLIENT
                        KhachHang kh = (KhachHang) TCPSocket.receiveMessageObject(socket);
                        if (addCustomer(kh)) {
                            TCPSocket.sendMessageObject(socket, "Thêm thành công!");
                        } else {
                            TCPSocket.sendMessageObject(socket, "Đã xảy ra lỗi!");
                        }
                        break;
                    case 2:      //PHƯƠNG THỨC TÍNH TIỀN PHÒNG
                        Locale localeVN = new Locale("vi", "VN");
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);

                        //LẤY SỐ NGÀY VÀ TÊN KHÁCH HÀNG TỪ CLIENT
                        String tenkhachhang = TCPSocket.receiveMessageObject(socket).toString();
                        int songay = Integer.parseInt(TCPSocket.receiveMessageObject(socket).toString());

                        //GỬI KẾT QUẢ TIỀN PHÒNG CHO CLIENT
                        String invoice = String.format("Tiền phong của khách hàng %s là: %s",
                                tenkhachhang, currencyFormat.format(createInvoice(songay, tenkhachhang)));
                        TCPSocket.sendMessageObject(socket, invoice);
                        break;
                    case 3:     //XEM DANH SÁCH KHÁCH HÀNG
                        ResultSet resultSet = SQLServer.getResultSet(SQLServer.getConnection(5549, "sa", "Phuonguno",
                                "QLKHACHSAN"), "SELECT * FROM KHACHHANG");
                        String listkh = String.format("%8s%30s%150s\n", "ID", "TÊN KHÁCH HÀNG", "LOAI PHÒNG");
                        while (resultSet.next()) {
                            listkh = listkh + String.format("%8s%30s%15s\n", resultSet.getInt("ID"),
                                    resultSet.getString("TENKHACHHANG"),
                                    resultSet.getString("LOAIPHONG"));
                        }
                        TCPSocket.sendMessageObject(socket, listkh);
                }
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC THÊM MỘT KHÁCH HÀNG VÀO DATABASE
    public static boolean addCustomer(KhachHang kh) {
        try {
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLKHACHSAN");
            String query = "INSERT INTO KHACHHANG VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, kh.getMakhachhang());
            preparedStatement.setString(2, kh.getTenkhachhang());
            preparedStatement.setString(3, kh.getLoaiphong());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //PHƯƠNG THỨC TÍNH TIỀN PHÒNG
    public static long createInvoice(int songay, String tenkhachhang) {
        try {
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLKHACHSAN");
            String query = "SELECT LOAIPHONG FROM KHACHHANG WHERE TENKHACHHANG = N'" + tenkhachhang + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String loaiphong;
            if (resultSet.next()) {
                loaiphong = resultSet.getNString("LOAIPHONG");
            } else {
                return 0;
            }
            long dongia;
            switch (loaiphong) {
                case "S":
                    dongia = 500000;
                    break;
                case "A":
                    dongia = 250000;
                    break;
                default:
                    dongia = 100000;
            }
            return songay * dongia;
        } catch (SQLException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
