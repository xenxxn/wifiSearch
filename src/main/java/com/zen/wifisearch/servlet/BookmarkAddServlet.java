package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/bookmarkAdd")
public class BookmarkAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String WF_ID = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            String BG_BM_NAME = request.getParameter("bookmark");
            String WF_BM_NAME = request.getParameter("WF_NAME");
            WF_ID = request.getParameter("WF_ID");
            BookmarkService.insertBookmark(BG_BM_NAME, WF_BM_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 리다이렉트 수행
        response.sendRedirect("info?wifi_id=" + WF_ID);
    }
}
