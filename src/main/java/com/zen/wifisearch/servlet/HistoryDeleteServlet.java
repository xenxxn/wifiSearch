package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/historyDelete")
public class HistoryDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int HT_ID = Integer.parseInt(request.getParameter("HT_ID"));
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            HistoryService.deleteHistory(HT_ID);
            System.out.println("성공");
        } catch (SQLException e) {
            System.out.println("실패");
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("history.jsp").forward(request, response);
    }
}
