package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.HistoryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/historyAdd")
public class HistoryAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        try{
            HistoryService.insertHistory(longitude, latitude);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
