package com.zen.wifisearch;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlite:C:/Users/ZEN/Desktop/제로베이스/과제/wifiSearch/identifier.sqlite";

    public static Connection getConnection() {
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false);
            rs = getWifiInfo(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
            System.out.println("연결 해제");
        }
    }
    private static ResultSet getWifiInfo(Connection connection) throws SQLException {
        String testSql = "SELECT * FROM WIFI";
        PreparedStatement pstmt = connection.prepareStatement(testSql);
        return pstmt.executeQuery();
    }

    public static void main(String[] args) throws IOException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection conn = connector.getConnection();
        connector.closeConnection(conn);
    }
}

