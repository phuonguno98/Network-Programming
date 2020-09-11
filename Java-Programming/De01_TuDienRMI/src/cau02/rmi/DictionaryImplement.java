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
public class DictionaryImplement extends UnicastRemoteObject implements DictionaryInterface {

    private static final long serialVersionUID = 1L;

    public DictionaryImplement() throws RemoteException {
    }

    @Override
    public String searchVieBasedOnEng(String word_eng) throws RemoteException {
        try {
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            String query = "SELECT VIE FROM DICTIONARY WHERE ENG = '" + word_eng + "'";
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, sqlusername);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("VIE");
            } else {
                return "Không tìm thấy!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DictionaryImplement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String searchEngBasedOnVie(String word_vie) throws RemoteException {
        try {
            String query = "SELECT ENG FROM DICTIONARY WHERE VIE = N'" + word_vie + "'";
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, sqlusername);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("ENG");
            } else {
                return "Không tìm thấy!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DictionaryImplement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String meaningEngWord(String word_eng) throws RemoteException {
        try {
            String query = "SELECT MEANING FROM DICTIONARY WHERE ENG = '" + word_eng + "'";
            int sqlport = 5549;
            String sqlusername = "sa";
            String sqlpassword = "Phuonguno";
            Connection connection = SQLServer.getConnection(sqlport, sqlusername, sqlpassword, sqlusername);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("MEANING");
            } else {
                return "Không tìm thấy!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DictionaryImplement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
