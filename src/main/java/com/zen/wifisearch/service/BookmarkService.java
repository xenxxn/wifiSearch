package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.model.Bookmark;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    public static void insertBookmark(String BG_NAME, String WF_ID) throws SQLException {
        Connection conn = null;
        String BG_BM_NAME = BookmarkGroupService.getBookmarkGroupName(BG_NAME);
        String WF_BM_NAME = WifiService.getWifiName(WF_ID);
        String insertQuery = "INSERT INTO BOOKMARK (BG_BM_NAME, WF_BM_NAME, BM_RG_DATE) VALUES (?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, BG_NAME);
            pstmt.setString(2, WF_ID);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (pstmt != null){
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static List<Bookmark> bookmarkList() throws SQLException {
        List<Bookmark> bookmarkList = new ArrayList<>();
        String selectQuery = "SELECT * FROM BOOKMARK";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try{
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement(selectQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookmark bm = new Bookmark();
                bm.setBM_ID(rs.getInt("BM_ID"));
                bm.setBG_BM_NAME(rs.getString("BG_BM_NAME"));
                bm.setWF_BM_NAME(rs.getString("WF_BM_NAME"));
                Timestamp rgTimestamp = rs.getTimestamp("BM_RG_DATE");
                if (rgTimestamp != null) {
                    bm.setBM_RG_DATE(rgTimestamp);
                }

                bookmarkList.add(bm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bookmarkList;
    }

    public static void deleteBookmark(int BM_ID) throws SQLException {
        String deleteQuery = "DELETE FROM BOOKMARK WHERE BM_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, BM_ID);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static Bookmark getBookmarkInfo(int BM_ID) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Bookmark bookmark = null;
        String selectQuery = "SELECT BM_ID, BG_BM_NAME, WF_BM_NAME,BM_RG_DATE FROM BOOKMARK WHERE BM_ID = ?";
        try{
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, BM_ID);
            rs = pstmt.executeQuery();

            if (rs.next()){
                bookmark = new Bookmark();
                bookmark.setBM_ID(BM_ID);
                bookmark.setBG_BM_NAME(rs.getString("BG_BM_NAME"));
                bookmark.setWF_BM_NAME(rs.getString("WF_BM_NAME"));
                Timestamp rgTimestamp = rs.getTimestamp("BM_RG_DATE");
                if (rgTimestamp != null) {
                    bookmark.setBM_RG_DATE(rgTimestamp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return bookmark;
    }
}



