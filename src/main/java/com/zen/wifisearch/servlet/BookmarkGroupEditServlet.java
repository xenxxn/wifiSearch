package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.BookmarkGroup;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bgIdParam = request.getParameter("BG_ID");
        if (bgIdParam != null) {
            int BG_ID = Integer.parseInt(bgIdParam);
            try {
                BookmarkGroup bookmarkGroup = BookmarkGroupService.getBookmarkGroupById(BG_ID); // 해당 ID의 정보를 가져오는 메소드를 가정
                request.setAttribute("bookmarkGroup", bookmarkGroup); // JSP에 정보 전달
            } catch (SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("bookmarkGroupEdit.jsp"); // 수정 페이지의 JSP 파일명으로 변경
            dispatcher.forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        int bgId = Integer.parseInt(request.getParameter("BG_ID"));
        String bgName = request.getParameter("BG_NAME");
        int bgOrder = Integer.parseInt(request.getParameter("BG_ORDER"));
        try {
            BookmarkGroupService.updateBookmarkGroup(bgId, bgName, bgOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("bookmarkGroupList");
    }

}
