package com.zen.wifisearch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class WifiAPI {
    public static String getWifiData(int start, int end) throws IOException, ParseException {
        String wifiDataList = "";

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
        urlBuilder.append("/" + URLEncoder.encode("786a754a5062756e373349746b6a4a", "UTF-8")); /* 인증키 */
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 */
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        wifiDataList = sb.toString();

        return wifiDataList;
    }

    public static Long getTotalCount() throws IOException, ParseException {
        String wifiDataList = "";

        int start = 1;
        int end = 1;

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
        urlBuilder.append("/" + URLEncoder.encode("786a754a5062756e373349746b6a4a", "UTF-8")); /* 인증키 */
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 */
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        wifiDataList = sb.toString();

        JSONObject result = (JSONObject) new JSONParser().parse(wifiDataList);
        JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");

        Long totalCount = (Long) data.get("list_total_count");

        return totalCount;
    }

    public static JSONArray getWifiAllData() throws ParseException, IOException{
        int itemCount = 1000;
        int start = 1;
        int end = itemCount;

        String orgStr = getWifiData(start, end);

        JSONObject result = (JSONObject) new JSONParser().parse(orgStr);
        JSONObject data = (JSONObject) result.get("TbPublicWifiInfo");
        JSONArray array = (JSONArray) data.get("row");

        StringBuilder sb = new StringBuilder();
        sb.append(array.toJSONString());
        sb.deleteCharAt(sb.length() - 1);

        Long totalCount = (Long) data.get("list_total_count");

        long loopCount = totalCount / itemCount;
        long remainCount = totalCount % itemCount;

        for(int i = 0; i < loopCount; i++){
            JSONObject tempResult = null;
            JSONObject tempData = null;
            JSONArray tempArray = null;
            StringBuilder tempBuilder = new StringBuilder();

            if(loopCount - 1 == i){
                start = end + 1;
                end += remainCount;

                tempResult = (JSONObject) new JSONParser().parse(getWifiData(start, end));
                tempData = (JSONObject) tempResult.get("TbPublicWifiInfo");
                tempArray = (JSONArray) tempData.get("row");
                tempBuilder.append(tempArray.toJSONString());
                tempBuilder.deleteCharAt(0);
                tempBuilder.insert(0, ",");

                sb.append(tempBuilder);
            }else{
                start = end + 1;
                end += itemCount;

                tempResult = (JSONObject) new JSONParser().parse(getWifiData(start, end));
                tempData = (JSONObject) tempResult.get("TbPublicWifiInfo");
                tempArray = (JSONArray) tempData.get("row");
                tempBuilder.append(tempArray.toJSONString());
                tempBuilder.deleteCharAt(0);
                tempBuilder.insert(0, ",");
                tempBuilder.deleteCharAt(tempBuilder.length() - 1);

                sb.append(tempBuilder);
            }
        }
        return (JSONArray) new JSONParser().parse(sb.toString());
    }

    public static JSONArray showNearWifiData(double wfX, double wfY){
        //가장 가까운 20개 데이터
        //쿼리로 데이터베이스에서 데이터를 가져와야함

        return null;
    }
}

