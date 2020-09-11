/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.tcpsocket.server;

import cau02.tcpsocket.question.Question;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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
        System.out.println("WELCOME TO SERVER!");
        Socket examSocket = examServerSocket.accept();
        ResultSet resultSet;    //ĐỐI TƯỢNG ĐỂ NHẬN VỀ TRUY VẤN DỮ LIỆU
        boolean result;
        
        do {
            Question question = new Question();
            resultSet = SQLServer.getResultSet(SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "CAUHOITRACNGHIEM"), "SELECT TOP 1 * FROM CAUHOI ORDER BY NEWID()");

            //LẤY CÁC CÂU HỎI RA TỪ ĐỐI TƯỢNG RESULTSET
            while (resultSet.next()) {
                question.setId(resultSet.getInt("ID"));
                question.setQuestion(resultSet.getString("CAUHOI"));
                question.setA(resultSet.getNString("A"));
                question.setB(resultSet.getNString("B"));
                question.setC(resultSet.getNString("C"));
                question.setD(resultSet.getNString("D"));
                question.setCorrect_answer(resultSet.getString("DAPANDUNG"));
            }

            //GỬI CÂU HỎI SANG CLIENT
            TCPSocket.sendMessageObject(examSocket, question);

            //NHẬN KẾT QUẢ TRẢ LỜI TỪ CLIENT
            String answer = (String) TCPSocket.receiveMessageObject(examSocket);

            //KIỂM TRA KẾT QUẢ TRẢ LỜI
            result = checkResult(answer, question);

            //GỬI KẾT QUẢ CUỐI CÙNG VỀ CHO CLIENT
            if (result) {
                TCPSocket.sendMessageObject(examSocket, "Đúng!");
            } else {
                TCPSocket.sendMessageObject(examSocket, "Sai!");
            }
        } while (result);

        examSocket.close();
        examServerSocket.close();
    }

    //PHƯƠNG THỨC KIỂM TRA KẾT QUẢ BÀI THI
    public static boolean checkResult(String answer, Question question) {
        if (question.getCorrect_answer().equalsIgnoreCase(answer)) {
            return true;
        }
        return false;
    }
}
