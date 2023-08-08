<%@ page import="com.zen.wifisearch.model.Wifi" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/info.css" type="text/css">
<html>
    <head>
        <script type="text/javascript" src="resources/js/getLocation.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <title>Title</title>
    </head>
    <body>
        <h1>와이파이 정보 구하기</h1>
        <a href="http://localhost:8080">홈</a> |
        <a href="http://localhost:8080">위치</a> |
        <a href="http://localhost:8080">히스토리 목록</a> |
        <a href="confirm.jsp">OPEN API 와이파이 정보 가져오기</a>
        <a href="http://localhost:8080">북마크 보기</a> |
        <a href="confirm.jsp">북마크 그룹 관리</a>
        <a href="bookmarkGroupAdd.jsp">추가test</a>
        <a href="bookmarkGroupEdit.jsp">수정test</a>
        <br><br>
        <form action="bookmarkAdd" method="post">
            <input type="hidden" name="WF_NAME" value="${wifi.getWF_NAME()}">
            <input type="hidden" name="WF_BM_NAME" value="${wifi.getWF_NAME()}"> <!-- Add this line -->
            <label for="bookmark">북마크 그룹 선택:</label>


            <select name="bookmark" id="bookmark">
                <option value="">북마크 그룹 이름 선택</option>
                <c:forEach items="${bookmarkGroup}" var="bookmarkGroup">
                    <option value="${bookmarkGroup.BG_NAME}">${bookmarkGroup.BG_NAME}</option>
                </c:forEach>
            </select>
            <button type="submit">북마크 추가하기</button>
        </form>
        <%
            if (request.getAttribute("wifi") == null) {
//                out.println("<p>해당 wifi_id에 대한 정보를 찾을 수 없습니다.</p>");
            } else {
                Wifi wifi = (Wifi) request.getAttribute("wifi");

        %>

        <table id = "wifi">
            <tbody>
                <tr>
                    <td class="column">거리(Km)</td>
                    <td><%= wifi.getDistance() %></td>
                </tr>
                <tr>
                    <td class="column">관리번호</td>
                    <td><%= wifi.getWF_ID() %></td>
                </tr>
                <tr>
                    <td class="column">자치구</td>
                    <td><%= wifi.getWF_BOROUGH() %></td>
                </tr>
                <tr>
                    <td class="column">와이파이명</td>
                    <td><%= wifi.getWF_NAME() %></td>
                </tr>
                <tr>
                    <td class="column">도로명주소</td>
                    <td><%= wifi.getWF_ST_ADDR() %></td>
                </tr>
                <tr>
                    <td class="column">상세주소</td>
                    <td><%= wifi.getWF_DT_ADDR() %></td>
                </tr>
                <tr>
                    <td class="column">설치위치(층)</td>
                    <td><%= wifi.getWF_FLOOR() %></td>
                </tr>
                <tr>
                    <td class="column">설치유형</td>
                    <td><%= wifi.getWF_INST_TYPE() %></td>
                </tr>
                <tr>
                    <td class="column">설치기관</td>
                    <td><%= wifi.getWF_INST_ORGN() %></td>
                </tr>
                <tr>
                    <td class="column">서비스구분</td>
                    <td><%= wifi.getWF_SERVICE() %></td>
                </tr>
                <tr>
                    <td class="column">망종류</td>
                    <td><%= wifi.getWF_NT_TYPE() %></td>
                </tr>
                <tr>
                    <td class="column">설치년도</td>
                    <td><%= wifi.getWF_YEAR() %></td>
                </tr>
                <tr>
                    <td class="column">실내외구분</td>
                    <td><%= wifi.getWF_INOUT() %></td>
                </tr>
                <tr>
                    <td class="column">WIFI접속환경</td>
                    <td><%= wifi.getWF_ENVIRONMENT()%></td>
                </tr>
                <tr>
                    <td class="column">X좌표</td>
                    <td><%= wifi.getWF_X() %></td>
                </tr>
                <tr>
                    <td class="column">Y좌표</td>
                    <td><%= wifi.getWF_Y()%></td>
                </tr>
                <tr>
                    <td class="column">작업일자</td>
                    <td><%= wifi.getWF_WORK_DATE()%></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
<%
    }
%>