package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.model.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {
    public static void insertBookmarkGroup(String BG_NAME, int BG_ORDER) throws SQLException {
        Connection conn = null;
        String insertQuery = "INSERT INTO bookmark_group (BG_NAME, BG_ORDER, BG_RG_DATE, BG_MD_DATE) " +
                "VALUES (?, ?, CURRENT_TIMESTAMP, null)";

        try {
            BookmarkGroup bg = new BookmarkGroup();
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
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

    public static void deleteBookmarkGroup(int BG_ID) throws SQLException {
        Connection conn = null;
        String deleteQuery = "DELETE FROM bookmark_group WHERE BG_ID = ?";
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, BG_ID);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateBookmarkGroup(int BG_ID, String BG_NAME, int BG_ORDER) throws SQLException {
        Connection conn = null;
        String updateQuery = "UPDATE bookmark_group " +
                "SET BG_NAME = ?, " +
                "    BG_ORDER = ?, " +
                "    BG_MD_DATE = CURRENT_TIMESTAMP " +
                "WHERE BG_ID = ?";
        try {
            conn = DatabaseConnector.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, BG_NAME);
            pstmt.setInt(2, BG_ORDER);
            pstmt.setInt(3, BG_ID);
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
    public static BookmarkGroup getBookmarkGroupById(int BG_ID) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BookmarkGroup bookmarkGroup = new BookmarkGroup();

        try {
            conn = DatabaseConnector.getConnection(); // 데이터베이스 연결을 얻는 방법
            String selectQuery = "SELECT BG_ID, BG_NAME, BG_ORDER FROM bookmark_group WHERE BG_ID = ?";
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, BG_ID);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookmarkGroup = new BookmarkGroup();
                bookmarkGroup.setBG_ID(rs.getInt("BG_ID"));
                bookmarkGroup.setBG_NAME(rs.getString("BG_NAME"));
                bookmarkGroup.setBG_ORDER(rs.getInt("BG_ORDER"));
            }
            System.out.println(bookmarkGroup);
        } catch (SQLException e) {
            e.printStackTrace();
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

        return bookmarkGroup;
    }

    public static List<BookmarkGroup> bookmarkGroupList() throws SQLException {
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        Connection conn = null;
        String selectQuery = "SELECT * FROM bookmark_group";
        try{
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
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
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return bookmarkGroupList;
    }

    public static String getBookmarkGroupName(String BG_NAME) throws SQLException {
        Connection conn = null;

        String selectQuery = "SELECT BG_NAME FROM bookmark_group WHERE BG_NAME = ?";
        try{
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
            pstmt.setString(1, BG_NAME);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                BG_NAME = rs.getString("BG_NAME");
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return BG_NAME;
    }
}
