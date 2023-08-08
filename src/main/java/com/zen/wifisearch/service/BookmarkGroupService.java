package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.model.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {
    public static void insertBookmarkGroup(String BG_NAME, int BG_ORDER) throws SQLException {
        Connection conn = null;
        String insertSql = "INSERT INTO bookmark_group (BG_NAME, BG_ORDER, BG_RG_DATE, BG_MD_DATE) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP, null)";

        try {
            BookmarkGroup bg = new BookmarkGroup();
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, BG_NAME);
            pstmt.setInt(2, BG_ORDER);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void deleteBookmarkGroup(){
        Connection conn = null;
        String deleteSql = "delete from bookmark_group";
    }

    public static void updateBookmarkGroup(int BG_ID, String updatedBG_NAME, int updatedBG_ORDER) throws SQLException {
        Connection conn = null;
        String updateSql = "UPDATE bookmark_group " +
                "SET BG_NAME = ?, " +
                "    BG_ORDER = ?, " +
                "    BG_MD_DATE = CURRENT_TIMESTAMP " +
                "WHERE BG_ID = ?";
        try {
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, updatedBG_NAME);
            pstmt.setInt(2, updatedBG_ORDER);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void selectBookmarkGroupInfo(){

    }

    public static List<BookmarkGroup> bookmarkGroupList(){
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        Connection conn = null;
        String selectSql = "select * from bookmark_group";
        try{
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectSql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookmarkGroup bg = new BookmarkGroup();
                bg.setBG_ID(rs.getInt("BG_ID"));
                bg.setBG_NAME(rs.getString("BG_NAME"));
                bg.setBG_ORDER(rs.getInt("BG_ORDER"));
                Timestamp rgTimestamp = rs.getTimestamp("BG_RG_DATE");
                if (rgTimestamp != null) {
                    bg.setBG_RG_DATE(rgTimestamp);
                }

                Timestamp mdTimestamp = rs.getTimestamp("BG_MD_DATE");
                if (mdTimestamp != null) {
                    bg.setBG_MD_DATE(mdTimestamp);
                }
                bookmarkGroupList.add(bg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookmarkGroupList;
    }
}
