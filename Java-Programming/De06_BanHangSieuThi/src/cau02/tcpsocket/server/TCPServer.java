/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import mylib.SQLServer;
import mylib.TCPSocket;
import cau02.tcpsocket.mathang.MatHang;
import cau02.tcpsocket.message.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author UNO
 */
public class TCPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG CẦN DÙNG
        int port = 3698;
        ServerSocket marketServerSocket = new ServerSocket(port);
        while (true) {
            Socket marketSocket = marketServerSocket.accept();
            Message message;

            //NHẬN THÔNG ĐIỆP TỪ CLIENT
            message = (Message) TCPSocket.receiveMessageObject(marketSocket);

            //XỬ LÝ THÔNG ĐIỆP
            switch (Integer.parseInt(message.getMessage())) {
                case Message.TIM_KIEM:
                    //TÌM MẶT HÀNG
                    int mahang = message.getMathang().getMahang();
                    message.setMathang(searchProduct(mahang));

                    //GỬI KẾT QUẢ VỀ CLIENT
                    TCPSocket.sendMessageObject(marketSocket, message);
                    break;
                case Message.LAP_HOA_DON:
                    //LẬP HÓA ĐƠN
                    message.setMessage(createReciept(message.getMapMatHang()));

                    //GỬI KẾT QUẢ VỀ CLIENT
                    TCPSocket.sendMessageObject(marketSocket, message);
            }
        }
    }

    //PHƯƠNG THỨC TÌM MỘT MẶT HÀNG THEO MÃ MẶT HÀNG
    public static MatHang searchProduct(int idProduct) throws SQLException, ClassNotFoundException {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        Connection connectionSQLServer = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "MATHANG");
        String query = "SELECT * FROM MATHANG WHERE MAHANG = " + idProduct;
        Statement statement = connectionSQLServer.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        MatHang mathang;
        if (resultset.next()) {
            mathang = new MatHang(idProduct, resultset.getString("TENHANG"), resultset.getDouble("GIABAN"), resultset.getInt("SLTONKHO"));
        } else {
            mathang = new MatHang();
        }
        return mathang;
    }

    //PHƯƠNG THỨC LẬP HÓA ĐƠN
    public static String createReciept(Map<Integer, Integer> mapMatHang) throws SQLException, ClassNotFoundException {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        String reciept = String.format("%30s", "=====THÔNG TIN HÓA ĐƠN=====") + "\n"
                + MatHang.INFO_PRODUCT_FORMAT;
        double thanhtien = 0;
        Connection connectionSQLServer = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "MATHANG");
        String query = "SELECT * FROM MATHANG WHERE MAHANG = ?";
        PreparedStatement preparedStatement = connectionSQLServer.prepareStatement(query);
        int flag = 0;   //XÁC ĐỊNH CÓ LỖI LẬP HÓA ĐƠN KHI @flag = 1
        for (Map.Entry<Integer, Integer> entry : mapMatHang.entrySet()) {
            preparedStatement.setInt(1, entry.getKey());
            ResultSet resultset = preparedStatement.executeQuery();
            if (!resultset.next()) {
                reciept = "Lỗi lập hóa đơn! Không tìm thấy mặt hàng có mã: " + entry.getKey();
                flag = 1;
                break;
            } else if (resultset.getInt("SLTONKHO") < entry.getValue()) {
                reciept = "Lỗi lâp hóa đơn! Không đủ số lượng cho mặt hàng có mã: " + entry.getKey();
                flag = 1;
                break;
            } else {
                thanhtien = thanhtien + entry.getValue() * resultset.getDouble("GIABAN");
                reciept = reciept + "\n" + String.format("%7s %25s %10s %15s", resultset.getInt("MAHANG"),
                        resultset.getString("TENHANG"), resultset.getDouble("GIABAN"), entry.getValue());
            }
        }
        if (flag == 0) {
            reciept = reciept + "\n" + String.format("%45s", "THÀNH TIỀN: ") + String.format("%15.0f", thanhtien);
        }
        return reciept;
    }
}
