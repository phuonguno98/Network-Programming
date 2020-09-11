/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi;

import cau02.rmi.mathang.MatHang;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.SQLServer;

/**
 *
 * @author UNO
 */
public class MiniGameImplements extends UnicastRemoteObject implements InterfaceMiniGame {

    private static final long serialVersionUID = 1L;

    public MiniGameImplements() throws RemoteException {
    }

    @Override
    public MatHang chooseMatHangRandom() {
        int sqlport = 5549;
        String sqlusername = "sa";
        String sqlpassword = "Phuonguno";
        MatHang mh = new MatHang();
        try {

            String database_name = "DANHSACHMATHANG";
            Connection conn = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, database_name);
            String query = "SELECT TOP 1 * FROM MATHANG ORDER BY NEWID()";  //CHỌN NGẪU NHIÊN MỘT SẢN PHẨM
            ResultSet resultSet = SQLServer.getResultSet(conn, query);
            if (resultSet.next()) {
                mh.setMahang(resultSet.getInt("MAHANG"));
                mh.setTenhang(resultSet.getString("TENHANG"));
                mh.setTrigia(resultSet.getDouble("TRIGIA"));
            }
            return mh;
        } catch (SQLException ex) {
            Logger.getLogger(MiniGameImplements.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int checkClientAnswer(double clientprice, MatHang mathang) {
        if (clientprice < mathang.getTrigia()) {    //GIÁ DỰ ĐOÁN NHỎ HƠN GIÁ ĐÚNG CỦA MẶT HÀNG
            return -1;
        } else if (clientprice > mathang.getTrigia()) {     //GIÁ DỰ ĐOÁN LỚN HƠN GIÁ ĐÚNG CỦA MẶT HÀNG
            return 1;
        } else {    //GIÁ DỰ ĐOÁN CHÍNH XÁC
            return 0;
        }
    }
}
