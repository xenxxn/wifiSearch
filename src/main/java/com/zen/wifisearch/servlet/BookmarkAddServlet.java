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
            String BG_ID = request.getParameter("bookmark");
            WF_ID = request.getParameter("WF_ID");
            BookmarkService.insertBookmark(Integer.parseInt(BG_ID), WF_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("info?wifi_id=" + WF_ID);
    }
}

