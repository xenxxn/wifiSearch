package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.WifiService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteWifiData")
public class GetDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (WifiService.selectWifiData() > 0){
                System.out.println("WifiService.selectWifiData()" + WifiService.selectWifiData());
                WifiService.deleteWifiData();
                System.out.println("삭제 완료");
                WifiService.insertWifiData();
                System.out.println("재삽입 완료");
            }else {
                WifiService.insertWifiData();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirm.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException  e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
