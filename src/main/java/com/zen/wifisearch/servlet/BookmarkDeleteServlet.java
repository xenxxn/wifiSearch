package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/bookmarkDelete")
public class BookmarkDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        int BM_ID = Integer.parseInt(request.getParameter("BM_ID"));
        try {
            BookmarkService.deleteBookmark(BM_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("bookmarkList");
    }
}
