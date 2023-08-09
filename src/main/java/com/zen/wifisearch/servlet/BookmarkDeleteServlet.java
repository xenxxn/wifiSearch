package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.Bookmark;
import com.zen.wifisearch.service.BookmarkService;

import javax.servlet.RequestDispatcher;
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
        String bmIdParam = request.getParameter("BM_ID");
        if (bmIdParam != null) {
            int BM_ID = Integer.parseInt(bmIdParam);
            try {
                Bookmark bookmark = BookmarkService.getBookmarkInfo(BM_ID);
                request.setAttribute("bookmark", bookmark);
            } catch (SQLException e) {
                e.printStackTrace();

            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookmarkDelete.jsp");
            dispatcher.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        int BM_ID = Integer.parseInt(request.getParameter("BM_ID"));
        try {
            BookmarkService.deleteBookmark(BM_ID);
            System.out.println("삭제완료");
        } catch (SQLException e) {
            System.out.println("실패");
            throw new RuntimeException(e);
        }
        response.sendRedirect("bookmarkList");
    }
}
