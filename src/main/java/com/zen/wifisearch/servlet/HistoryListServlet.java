package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.History;
import com.zen.wifisearch.service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/history")
public class HistoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<History> historyList = null;
        try {
            historyList = HistoryService.historyList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("history.jsp").forward(request, response);
    }
}
