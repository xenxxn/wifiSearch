package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.Bookmark;
import com.zen.wifisearch.model.BookmarkGroup;
import com.zen.wifisearch.service.BookmarkGroupService;
import com.zen.wifisearch.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookmarkList")
public class BookmarkListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Bookmark> bookmarkList = null;
        try {
            bookmarkList = BookmarkService.bookmarkList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("bookmarkList", bookmarkList);
        request.getRequestDispatcher("bookmarkList.jsp").forward(request, response);
    }
}
