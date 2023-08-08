package com.zen.wifisearch;

import com.zen.wifisearch.service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/bookmarkGroupEdit")
public class BookmarkGroupEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");


        RequestDispatcher dispatcher = request.getRequestDispatcher("bookmarkGroupEdit.jsp");
        dispatcher.forward(request, response);
    }
}
