/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau02.rmi;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.SQLServer;

/**
 *
 * @author UNO
 */
public class WordImplement extends UnicastRemoteObject implements WordInterface {

    public WordImplement() throws RemoteException {
    }

    @Override
    public String convertAcronymToFull(String acronym) throws RemoteException {
        try {
            String query = "SELECT WORDFULL FROM WORD WHERE ACRONYM = N'" + acronym + "'";
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "WORD");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("WORDFULL");
            } else {
                return "Không tìm thấy!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(WordImplement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String convertFullToAcronym(String word_full) throws RemoteException {
        try {
            String query = "SELECT ACRONYM FROM WORD WHERE WORDFULL = N'" + word_full + "'";
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, "WORD");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("ACRONYM");
            } else {
                return "Không tìm thấy!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(WordImplement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
