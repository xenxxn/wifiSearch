package com.zen.wifisearch;

import com.zen.wifisearch.model.Wifi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static com.zen.wifisearch.service.WifiService.selectNearbyWifiData;

@WebServlet("/nearByWifiData")
public class NearByWifiDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));

        List<Wifi> nearbyWifiData = null;
        try {
            nearbyWifiData = selectNearbyWifiData(latitude, longitude);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        for (Wifi wifi : nearbyWifiData) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("WF_ID", wifi.getWF_ID());
                jsonObject.put("WF_BOROUGH", wifi.getWF_BOROUGH());
                jsonObject.put("WF_NAME", wifi.getWF_NAME());
                jsonObject.put("WF_ST_ADDR", wifi.getWF_ST_ADDR());
                jsonObject.put("WF_DT_ADDR", wifi.getWF_DT_ADDR());
                jsonObject.put("WF_FLOOR", wifi.getWF_FLOOR());
                jsonObject.put("WF_INST_TYPE", wifi.getWF_INST_TYPE());
                jsonObject.put("WF_INST_ORGN", wifi.getWF_INST_ORGN());
                jsonObject.put("WF_SERVICE", wifi.getWF_SERVICE());
                jsonObject.put("WF_NT_TYPE", wifi.getWF_NT_TYPE());
                jsonObject.put("WF_YEAR", wifi.getWF_YEAR());
                jsonObject.put("WF_INOUT", wifi.getWF_INOUT());
                jsonObject.put("WF_ENVIRONMENT", wifi.getWF_ENVIRONMENT());
                jsonObject.put("WF_Y", wifi.getWF_Y());
                jsonObject.put("WF_X", wifi.getWF_X());
                jsonObject.put("WF_WORK_DATE", wifi.getWF_WORK_DATE());
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonArray.add(jsonObject);
        }

        String jsonData = jsonArray.toString();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("wifiData", nearbyWifiData);
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
    }
}
