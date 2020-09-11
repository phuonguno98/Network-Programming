/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain methods serve exchanging data with Database of SQL Server
 * 
 * @author UNO
 */
public class SQLServer {

    /**
     * METHOD TO GET CONNECTION CONNECTED DATABASE
     * 
     * @param port is port of SQL Server
     * @param username name to login Database
     * @param password password to login Database
     * @param database_name name of Database
     * @return a Connection to connect Database of database_name
     */
    public static Connection getConnection(int port, String username, String password, String database_name) {
        try {
            String url = "jdbc:sqlserver://localhost:" + port + ";databaseName=" + database_name
                    + ";user=" + username + ";password=" + password + ";";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * METHOD TO GET RESULTSET OF QUERY TO DATABASE
     * 
     * @param conn connection has been connected the Database
     * @param query the string query
     * @return a ResultSet which contains records of Query
     */

    public static ResultSet getResultSet(Connection conn, String query) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
