/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import cau02.tcpsocket.message.Message;
import cau02.tcpsocket.question.Question;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mylib.SQLServer;
import mylib.TCPSocket;

/**
 *
 * @author UNO
 */
public class TCPServer {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        //KHAI BÁO CÁC ĐỐI TƯỢNG CẦN DÙNG
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        int port = 6789;
        ServerSocket examServerSocket = new ServerSocket(port);
        Socket examSocket = examServerSocket.accept();
        ResultSet resultSet;    //ĐỐI TƯỢNG ĐỂ NHẬN VỀ TRUY VẤN DỮA LIỆU
        Message mess = new Message();   //LỚP Message CHỨA NỘI DUNG TRAO ĐỔI GIỮA SERVER VÀ CLIENT
        ArrayList<Question> listquestions = new ArrayList<>();

        //KẾT NỐI CƠ SỞ DỮ LIỆU ĐỂ CHỌN NGẪU NHIÊN 3 CÂU HỎI
        resultSet = getResultSet(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "CAUHOITRACNGHIEM"), "SELECT TOP 3 * FROM CAUHOI ORDER BY NEWID()");

        //LẤY CÁC CÂU HỎI RA TỪ ĐỐI TƯỢNG RESULTSET
        while (resultSet.next()) {
            Question question = new Question();
            question.setId(resultSet.getInt("ID"));
            question.setQuestion(resultSet.getString("CAUHOI"));
            question.setA(resultSet.getNString("A"));
            question.setB(resultSet.getNString("B"));
            question.setC(resultSet.getNString("C"));
            question.setD(resultSet.getNString("D"));
            question.setCorrect_answer(resultSet.getString("DAPANDUNG"));
            listquestions.add(question);    //GÁN CÂU HỎI VÀO DANH SÁCH CÂU HỎI
        }

        //GỬI BỘ CÂU HỎI SANG CLIENT
        mess.setQuestions(listquestions);
        TCPSocket.sendMessageObject(examSocket, mess);

        //NHẬN KẾT QUẢ TRẢ LỜI TỪ CLIENT
        mess = (Message) TCPSocket.receiveMessageObject(examSocket);

        //KIỂM TRA KẾT QUẢ TRẢ LỜI
        String result = checkResult(mess.getAnswer(), listquestions);

        //GỬI KẾT QUẢ CUỐI CÙNG VỀ CHO CLIENT
        TCPSocket.sendMessageObject(examSocket, result);
    }

    //PHƯƠNG THỨC TRUY VẤN DỮ LIỆU
    public static ResultSet getResultSet(Connection conn, String query) throws SQLException, SQLException, ClassNotFoundException {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "CAUHOITRACNGHIEM");  //KẾT NỐI CƠ SỞ DỮ LIỆU
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    //PHƯƠNG THỨC KIỂM TRA KẾT QUẢ BÀI THI
    public static String checkResult(ArrayList<String> answer, ArrayList<Question> correct) {
        int result = 0;
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).equalsIgnoreCase(correct.get(i).getCorrect_answer())) {
                result++;
            }
        }
        if (result >= 2) {
            return "Đỗ";
        }
        return "Trượt";
    }
}
