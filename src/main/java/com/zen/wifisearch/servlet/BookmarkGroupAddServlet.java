package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/bookmarkGroupAdd")
public class BookmarkGroupAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8"); // 요청의 문자 인코딩 설정
            response.setContentType("text/html; charset=UTF-8"); // 응답의 문자 인코딩 설정

            String BG_NAME = request.getParameter("BG_NAME");
            int BG_ORDER = Integer.parseInt(request.getParameter("BG_ORDER"));
            BookmarkGroupService.insertBookmarkGroup(BG_NAME, BG_ORDER);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("bookmarkGroupAdd.jsp");
        dispatcher.forward(request, response);
    }
}
