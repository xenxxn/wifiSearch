package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.BookmarkGroup;
import com.zen.wifisearch.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookmarkGroupList")
public class BookmarkGroupListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<BookmarkGroup> bookmarkList = null;
        try {
            bookmarkList = BookmarkGroupService.bookmarkGroupList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("bookmarkList", bookmarkList);
        request.getRequestDispatcher("bookmarkGroupList.jsp").forward(request, response);
    }
}
