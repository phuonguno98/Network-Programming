/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.udpsocket.server;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.SQLServer;
import mylib.UDPSocket;

/**
 *
 * @author UNO
 */
public class UDPServer {

    //ĐỊNH DẠNG TIỀN TỆ VIỆT NAM
    public static final Locale localVN = new Locale("vi", "VN");
    public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localVN);

    public static void main(String[] args) {
        UDPSocket.port = 7798;
        long amount;
        boolean result_transaction;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(UDPSocket.port);

            while (true) {
                int function = Integer.parseInt(UDPSocket.receiveObjectMessage(datagramSocket).toString());
                switch (function) {
                    case 1:     //THU TIỀN
                        amount = Long.parseLong(UDPSocket.receiveObjectMessage(datagramSocket).toString());  //XÁC ĐỊNH SỐ TIỀN GIAO DỊCH
                        result_transaction = deposit(amount);   //THỰC HIỆN GIAO DỊCH THÊM TIỀN

                        //GỬI KẾT QUẢ VÈ CLIENT
                        if (result_transaction) {
                            UDPSocket.sendMessage(datagramSocket, "Giao dịch thành công!", UDPSocket.IPAddress, UDPSocket.port);
                        } else {
                            UDPSocket.sendMessage(datagramSocket, "Giao dịch thất bại!", UDPSocket.IPAddress, UDPSocket.port);
                        }
                        break;
                    case 2:     //CHI TIỀN
                        amount = Long.parseLong(UDPSocket.receiveObjectMessage(datagramSocket).toString());  //XÁC ĐỊNH SỐ TIỀN GIAO DỊCH
                        result_transaction = recharge(amount);  //THỰC HIỆN GIAO DỊCH

                        //GỬI KẾT QUẢ VỀ CLIENT
                        if (result_transaction) {
                            UDPSocket.sendMessage(datagramSocket, "Giao dịch thành công!", UDPSocket.IPAddress, UDPSocket.port);
                        } else {
                            UDPSocket.sendMessage(datagramSocket, "Giao dịch thất bại!", UDPSocket.IPAddress, UDPSocket.port);
                        }
                        break;
                    case 3:     //XEM SỐ DƯ
                        amount = getBalance();      //TRUY VẤN SỐ DƯ CỦA TÀI KHOẢN

                        //GỬI KẾT QUẢ VỀ CLIENT
                        UDPSocket.sendMessage(datagramSocket, currencyFormat.format(amount), UDPSocket.IPAddress, UDPSocket.port);
                        break;
                    case 4:     //XEM LỊCH SỬ THU/CHI
                        String history_transaction = getHistoryTransaction();   //SAO KÊ LỊCH SỬ GIAO DỊCH

                        //GỬI KẾT QUẢ VỀ CLIENT
                        UDPSocket.sendMessage(datagramSocket, history_transaction, UDPSocket.IPAddress, UDPSocket.port);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //PHƯƠNG THỨC THU TIỀN
    public static boolean deposit(long amount_deposit) {   //@amount là số tiền thu vào
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            //LẤY SỐ DƯ CỦA TÀI KHOẢN TẠI THỜI ĐIỂM GIAO DỊCH
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "SOTAICHINH");
            String query = "SELECT BALANCE FROM SOTAICHINH WHERE ID IN (SELECT MAX(ID) FROM SOTAICHINH)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            long balance;
            if (resultSet.next()) {
                balance = resultSet.getLong("BALANCE") + amount_deposit;
            } else {
                balance = 0;
            }

            //CẬP NHẬT TÀI KHOẢN
            return updateTransaction(balance, amount_deposit, "DEPOSIT");
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //PHƯƠNG THỨC CHI TIỀN
    public static boolean recharge(long amount_withdraw) { //@amount_withdraw là số tiền chi ra
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            //LẤY SỐ DƯ CỦA TÀI KHOẢN TẠI THỜI ĐIỂM GIAO DỊCH
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "SOTAICHINH");
            String query = "SELECT BALANCE FROM SOTAICHINH WHERE ID IN (SELECT MAX(ID) FROM SOTAICHINH)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            long balance;
            if (resultSet.next()) {
                balance = resultSet.getLong("BALANCE") - amount_withdraw;
            } else {
                return false;
            }

            //CẬP NHẬT TÀI KHOẢN
            return updateTransaction(balance, amount_withdraw, "RECHARGE");
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //PHƯƠNG THỨC TRUY VẤN SỐ DƯ
    public static long getBalance() {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "SOTAICHINH");
            String query = "SELECT BALANCE FROM SOTAICHINH WHERE ID IN (SELECT MAX(ID) FROM SOTAICHINH)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("BALANCE");
            }
            System.out.println("SUSSECCFUL!");
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //PHƯƠNG THỨC TRUY VẤN LỊCH SỬ GIAO DỊCH
    public static String getHistoryTransaction() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa");
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            String result = String.format("%5s%30s%20s%20s", "ID", "THỜI GIAN", "LOẠI GIAO DỊCH", "SỐ TIỀN");
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "SOTAICHINH");
            String query = "SELECT * FROM SOTAICHINH";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = result + "\n" + String.format("%5s%30s%20s%20s", resultSet.getInt("ID"),
                        dateFormat.format(resultSet.getTimestamp("DATE_TRANSACTION")), resultSet.getString("TYPE_TRANSACTION"),
                        currencyFormat.format(resultSet.getLong("AMOUNTS_TRANSACTION")));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //PHƯƠNG THỨC CẬP NHẬT LỊCH SỬ GIAO DỊCH
    public static boolean updateTransaction(long balance, long amount, String type_tracsaction) {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        try {
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "SOTAICHINH");
            String query = "INSERT INTO SOTAICHINH VALUES (?, ?, GETDATE(), ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setLong(1, balance);
            preparedStatement.setString(2, type_tracsaction);
            preparedStatement.setLong(3, amount);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
