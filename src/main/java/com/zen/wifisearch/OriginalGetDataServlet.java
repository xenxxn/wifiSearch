package com.zen.wifisearch;

import com.zen.wifisearch.service.WifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/insertWifiData")
public class OriginalGetDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WifiService.insertWifiData();
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirm.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException  e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}