package com.zen.wifisearch.service;

import com.zen.wifisearch.DatabaseConnector;
import com.zen.wifisearch.WifiAPI;
import com.zen.wifisearch.model.Wifi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class WifiService {
    private static final String INSERT_QUERY = "INSERT INTO wifi (WF_ID, WF_BOROUGH, WF_NAME, WF_ST_ADDR," +
            "WF_DT_ADDR, WF_FLOOR, WF_INST_TYPE, WF_INST_ORGN, WF_SERVICE, WF_NT_TYPE," +
            "WF_YEAR, WF_INOUT, WF_ENVIRONMENT, WF_Y, WF_X, WF_WORK_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM wifi";

    public static void insertWifiData() throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            JSONArray wifiDataArray = WifiAPI.getWifiAllData();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_QUERY);
            conn.setAutoCommit(false);

            for (Object wifiData : wifiDataArray) {
                JSONObject wifiJson = (JSONObject) wifiData;
                Wifi wifi = parseWifiJson(wifiJson);

                pstmt.setString(1, wifi.getWF_ID());
                pstmt.setString(2, wifi.getWF_BOROUGH());
                pstmt.setString(3, wifi.getWF_NAME());
                pstmt.setString(4, wifi.getWF_ST_ADDR());
                pstmt.setString(5, wifi.getWF_DT_ADDR());
                pstmt.setString(6, wifi.getWF_FLOOR());
                pstmt.setString(7, wifi.getWF_INST_TYPE());
                pstmt.setString(8, wifi.getWF_INST_ORGN());
                pstmt.setString(9, wifi.getWF_SERVICE());
                pstmt.setString(10, wifi.getWF_NT_TYPE());
                pstmt.setInt(11, wifi.getWF_YEAR());
                pstmt.setString(12, wifi.getWF_INOUT());
                pstmt.setString(13, wifi.getWF_ENVIRONMENT());
                pstmt.setDouble(14, wifi.getWF_Y());
                pstmt.setDouble(15, wifi.getWF_X());
                pstmt.setString(16, wifi.getWF_WORK_DATE());
                pstmt.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }finally {
                conn.close();
            }
        }
    }

    private static Wifi parseWifiJson(JSONObject wifiJson) {
        Wifi wifi = new Wifi();
        wifi.setWF_ID((String) wifiJson.get("X_SWIFI_MGR_NO"));
        wifi.setWF_BOROUGH((String) wifiJson.get("X_SWIFI_WRDOFC"));
        wifi.setWF_NAME((String) wifiJson.get("X_SWIFI_MAIN_NM"));
        wifi.setWF_ST_ADDR((String) wifiJson.get("X_SWIFI_ADRES1"));
        wifi.setWF_DT_ADDR((String) wifiJson.get("X_SWIFI_ADRES2"));
        wifi.setWF_FLOOR((String) wifiJson.get("X_SWIFI_INSTL_FLOOR"));
        wifi.setWF_INST_TYPE((String) wifiJson.get("X_SWIFI_INSTL_TY"));
        wifi.setWF_INST_ORGN((String) wifiJson.get("X_SWIFI_INSTL_MBY"));
        wifi.setWF_SERVICE((String) wifiJson.get("X_SWIFI_SVC_SE"));
        wifi.setWF_NT_TYPE((String) wifiJson.get("X_SWIFI_CMCWR"));
        wifi.setWF_YEAR(Integer.parseInt((String) wifiJson.get("X_SWIFI_CNSTC_YEAR")));
        wifi.setWF_INOUT((String) wifiJson.get("X_SWIFI_INOUT_DOOR"));
        wifi.setWF_ENVIRONMENT((String) wifiJson.get("X_SWIFI_REMARS3"));
        wifi.setWF_Y(Double.parseDouble((String) wifiJson.get("LNT")));
        wifi.setWF_X(Double.parseDouble((String) wifiJson.get("LAT")));
        wifi.setWF_WORK_DATE((String) wifiJson.get("WORK_DTTM"));
        return wifi;
    }

    public static void deleteWifiData() throws SQLException {
        Connection conn = null;
        String deleteSql = "delete from wifi";
        try {
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleteSql);
            conn.setAutoCommit(false);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("와이파이 테이블 삭제 완료");
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    public static int selectWifiData() throws SQLException {
        int count = 0;
        Connection conn = null;
        String selectSql = "select * from wifi ORDER BY ROWID LIMIT 1";
        try{
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(selectSql);
            if (pstmt != null) {
                count = 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conn.close();
        }
        return count;
    }

    public static List<Wifi> selectNearbyWifiData(double latitude, double longitude) throws SQLException {
        List<Wifi> nearbyWifiData = new ArrayList<>();

        Connection conn = null;
        String nearSelectSql = "SELECT *,\n" +
                "       (6371000 * acos(cos(radians(:latitude)) * cos(radians(WF_Y)) * cos(radians(WF_X) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(WF_Y)))) AS distance\n" +
                "FROM Wifi\n" +
                "ORDER BY distance ASC\n" +
                "LIMIT 20";

        try {
            conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(nearSelectSql);
            pstmt.setDouble(1, latitude);
            pstmt.setDouble(2, longitude);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setWF_ID(rs.getString("WF_ID"));
                wifi.setWF_BOROUGH(rs.getString("WF_BOROUGH"));
                wifi.setWF_NAME(rs.getString("WF_NAME"));
                wifi.setWF_ST_ADDR(rs.getString("WF_ST_ADDR"));
                wifi.setWF_DT_ADDR(rs.getString("WF_DT_ADDR"));
                wifi.setWF_FLOOR(rs.getString("WF_FLOOR"));
                wifi.setWF_INST_TYPE(rs.getString("WF_INST_TYPE"));
                wifi.setWF_INST_ORGN(rs.getString("WF_INST_ORGN"));
                wifi.setWF_SERVICE(rs.getString("WF_SERVICE"));
                wifi.setWF_NT_TYPE(rs.getString("WF_NT_TYPE"));
                wifi.setWF_YEAR(rs.getInt("WF_YEAR"));
                wifi.setWF_INOUT(rs.getString("WF_INOUT"));
                wifi.setWF_ENVIRONMENT(rs.getString("WF_ENVIRONMENT"));
                wifi.setWF_Y(rs.getDouble("WF_Y"));
                wifi.setWF_X(rs.getDouble("WF_X"));
                wifi.setWF_WORK_DATE(rs.getString("WF_WORK_DATE"));
                nearbyWifiData.add(wifi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return nearbyWifiData;
    }

}