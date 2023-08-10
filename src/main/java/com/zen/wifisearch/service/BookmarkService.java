package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.model.Bookmark;
import com.zen.wifisearch.model.BookmarkGroup;
import com.zen.wifisearch.model.Wifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    public static void insertBookmark(int BG_ID, String WF_ID) throws SQLException {
        Connection conn = null;
        String insertQuery = "INSERT INTO BOOKMARK (BG_ID, WF_ID, BM_RG_DATE) VALUES (?, ?, DATETIME('NOW', 'LOCALTIME'))";
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            String BG_NAME = BookmarkGroupService.getBookmarkGroupName(BG_ID);
            String WF_NAME = WifiService.getWifiName(WF_ID);
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, BG_ID);
            pstmt.setString(2, WF_ID);
            pstmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }


    public static List<Bookmark> bookmarkList() throws SQLException {
        List<Bookmark> bookmarkList = new ArrayList<>();
        String selectQuery = "SELECT B.BM_ID, B.BG_ID, B.WF_ID, B.BM_RG_DATE, BG.BG_NAME, W.WF_NAME "
                + "FROM BOOKMARK B "
                + "JOIN BOOKMARKGROUP BG ON B.BG_ID = BG.BG_ID "
                + "JOIN Wifi W ON B.WF_ID = W.WF_ID";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement(selectQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bookmark bm = new Bookmark();
                bm.setBM_ID(rs.getInt("BM_ID"));
                bm.setBM_RG_DATE(rs.getTimestamp("BM_RG_DATE"));

                BookmarkGroup bg = new BookmarkGroup();
                bg.setBG_ID(rs.getInt("BG_ID"));
                bg.setBG_NAME(rs.getString("BG_NAME"));
                bm.setBookmarkGroup(bg);

                Wifi wf = new Wifi();
                wf.setWF_ID(rs.getString("WF_ID"));
                wf.setWF_NAME(rs.getString("WF_NAME"));
                bm.setWifi(wf);

                bookmarkList.add(bm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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
        String selectQuery = "SELECT B.BM_ID, B.BM_RG_DATE, BG.BG_NAME, W.WF_NAME "
                + "FROM BOOKMARK B "
                + "JOIN BOOKMARKGROUP BG ON B.BG_ID = BG.BG_ID "
                + "JOIN Wifi W ON B.WF_ID = W.WF_ID "
                + "WHERE B.BM_ID = ?";
        try {
            conn = DatabaseConnector.getConnection();
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, BM_ID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookmark = new Bookmark();
                bookmark.setBM_ID(BM_ID);
                bookmark.setBM_RG_DATE(rs.getTimestamp("BM_RG_DATE"));

                BookmarkGroup bg = new BookmarkGroup();
                bg.setBG_NAME(rs.getString("BG_NAME"));
                bookmark.setBookmarkGroup(bg);

                Wifi wf = new Wifi();
                wf.setWF_NAME(rs.getString("WF_NAME"));
                bookmark.setWifi(wf);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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



