package com.zen.wifisearch.servlet;

import com.zen.wifisearch.service.BookmarkGroupService;
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
        try {
            request.setCharacterEncoding("UTF-8"); // 요청의 문자 인코딩 설정
            response.setContentType("text/html; charset=UTF-8"); // 응답의 문자 인코딩 설정
            String BG_BM_NAME = request.getParameter("bookmark");
            String WF_BM_NAME = request.getParameter("WF_NAME");
            BookmarkService.insertBookmark(BG_BM_NAME, WF_BM_NAME);
            System.out.println("성공");
        } catch (SQLException e) {
            System.out.println("실패");
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("info.jsp");
        dispatcher.forward(request, response);
    }
}