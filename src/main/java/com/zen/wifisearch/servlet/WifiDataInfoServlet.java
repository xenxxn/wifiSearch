package com.zen.wifisearch.servlet;

import com.zen.wifisearch.model.BookmarkGroup;
import com.zen.wifisearch.model.Wifi;
import com.zen.wifisearch.service.BookmarkGroupService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zen.wifisearch.service.WifiService.wifiInfo;

@WebServlet("/info")
public class WifiDataInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String wifi_id = request.getParameter("wifi_id");
        Wifi wifi = null;
        if (wifi_id != null) {
            wifi = wifiInfo(wifi_id);
        }
        List<BookmarkGroup> bookmarkGroupList = new ArrayList<>();
        try {
            bookmarkGroupList = BookmarkGroupService.bookmarkGroupList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=UTF-8");
        request.setAttribute("bookmarkGroup", bookmarkGroupList);
        request.setAttribute("wifi", wifi);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}
