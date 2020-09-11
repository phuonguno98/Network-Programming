/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import cau02.tcpsocket.message.Message;
import cau02.tcpsocket.sinhvien.SinhVien;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.*;

/**
 *
 * @author UNO
 */
public class TCPServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG SỬ DỤNG
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        final int port = 1256;
        ServerSocket managerSocket = new ServerSocket(port);
        while (true) {
            Socket socket = managerSocket.accept();
            Message message;
            ResultSet resultSet;
            String query;   //CHỨA CÂU LỆNH TRUY VẤN

            //NHẬN THÔNG ĐIỆP TỪ CLIENT
            message = (Message) TCPSocket.receiveMessageObject(socket);

            //XỬ LÝ THÔNG ĐIỆP
            switch (Integer.parseInt(message.getMessage())) { //XÁC ĐỊNH CHỨC NĂNG CỦA THÔNG ĐIỆP
                case Message.XEM_DS_SINH_VIEN:
                    //LẤY DANH SÁCH SINH VIÊN
                    query = "SELECT * FROM SINHVIEN";
                    message.setListSinhViens(getListSinhVien(SQLServer.getResultSet(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLSINHVIEN"), query)));

                    //GỬI KẾT QUẢ CHO CLIENT
                    TCPSocket.sendMessageObject(socket, message);
                    break;
                case Message.XEM_THONG_TIN_SV:
                    //TRUY VẤN DỮ LIỆU
                    query = "SELECT * FROM SINHVIEN WHERE MASV = '" + message.getSinhvien().getMasv().toUpperCase() + "'";
                    resultSet = SQLServer.getResultSet(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLSINHVIEN"), query);
                    message.setListSinhViens(getListSinhVien(resultSet));

                    //GỬI KẾT QUẢ CHO CLIENT
                    if (!message.getListSinhViens().isEmpty()) {
                        TCPSocket.sendMessageObject(socket, message.getListSinhViens().get(0).toString());
                    } else {
                        TCPSocket.sendMessageObject(socket, "Không tìm thấy sinh viên có mã số " + message.getSinhvien().getMasv());
                    }
                    break;
                case Message.THEM_SINH_VIEN:
                    int result = addNewStudent(message.getSinhvien());
                    if (result > 0) {
                        TCPSocket.sendMessageObject(socket, "Đã thêm thành công!");
                    } else {
                        TCPSocket.sendMessageObject(socket, "Thêm không thành công!");
                    }
                    break;
                case Message.LOC_SINH_VIEN:
                    if (message.getTypekeyword().equalsIgnoreCase("Year")) {    //LỌC THEO NĂM
                        query = "SELECT * FROM SINHVIEN WHERE YEAR(NGAYSINH) = " + message.getSinhvien().getNgaysinh().getYear();
                    } else //LỌC THEO QUÊ QUÁN
                    {
                        query = "SELECT * FROM SINHVIEN WHERE QUEQUAN = N'" + message.getSinhvien().getQuequan().toUpperCase() + "'";
                    }

                    //THỰC HIỆN TRUY VẤN DỮ LIỆU
                    resultSet = SQLServer.getResultSet(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLSINHVIEN"), query);
                    message.setListSinhViens(getListSinhVien(resultSet));
                    System.out.println(message.getListSinhViens().toString());

                    //GỬI KẾT QUẢ VỀ CLIENT
                    TCPSocket.sendMessageObject(socket, message);
            }
        }
    }

    //PHƯƠNG THỨC LẤY DANH SÁCH SINH VIÊN
    public static ArrayList<SinhVien> getListSinhVien(ResultSet resultSet) throws SQLException {
        ArrayList<SinhVien> listSV = new ArrayList<>();
        while (resultSet.next()) {
            listSV.add(new SinhVien(resultSet.getString("HOTEN"),
                    resultSet.getString("MASV"),
                    resultSet.getDate("NGAYSINH"),
                    resultSet.getString("QUEQUAN")));
        }
        return listSV;
    }

    //PHƯƠNG THỨC THÊM SINH VIÊN
    public static int addNewStudent(SinhVien sinhVien) throws SQLException, ClassNotFoundException {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        String query = "INSERT INTO SINHVIEN VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "QLSINHVIEN").prepareStatement(query);
        preparedStatement.setString(1, sinhVien.getTensv());
        preparedStatement.setString(2, sinhVien.getMasv());
        preparedStatement.setDate(3, new java.sql.Date(sinhVien.getNgaysinh().getTime()));
        preparedStatement.setString(4, sinhVien.getQuequan());
        return preparedStatement.executeUpdate();
    }
}
