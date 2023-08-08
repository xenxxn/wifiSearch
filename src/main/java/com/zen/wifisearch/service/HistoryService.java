package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.model.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    public static void insertHistory(double longitude, double latitude) throws SQLException {
        Connection conn = null;

        String insertQuery = "INSERT INTO history (HT_X, HT_Y, HT_DATE) VALUES (?, ?, CURRENT_TIMESTAMP)";

        try{
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setDouble(1, longitude);
            pstmt.setDouble(2, latitude);
            pstmt.executeUpdate();
            conn.commit();
        }catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static List<History> historyList() throws SQLException {
        List<History> historyList = new ArrayList<>();
        String selectQuery = "SELECT * from HISTORY";
        Connection conn = null;
        try{
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                History history = new History();
                history.setHT_ID(rs.getInt("HT_ID"));
                history.setHT_X(rs.getDouble("HT_X"));
                history.setHT_Y(rs.getDouble("HT_Y"));
                Timestamp rgTimestamp = rs.getTimestamp("HT_DATE");
                if (rgTimestamp != null) {
                    history.setHT_DATE(rgTimestamp);
                }
                historyList.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return historyList;
    }

    public static void deleteHistory(int HT_ID) throws SQLException {
        String deleteQuery = "DELETE FROM history WHERE HT_ID = ?";
        Connection conn = null;
        try{
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, HT_ID);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
