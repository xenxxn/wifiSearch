package com.zen.wifisearch;

import com.zen.wifisearch.model.BookmarkGroup;
import com.zen.wifisearch.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmarkGroupList")
public class BookmarkGroupListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<BookmarkGroup> bookmarkList = BookmarkGroupService.bookmarkGroupList();
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("bookmarkList", bookmarkList);
        request.getRequestDispatcher("bookmarkGroupList.jsp").forward(request, response);
        System.out.println(bookmarkList);
    }
}
