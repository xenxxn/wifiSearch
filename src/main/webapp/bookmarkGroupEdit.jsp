<%@ page import="com.zen.wifisearch.model.BookmarkGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="resources/css/info.css" type="text/css">
<html>
    <head>
        <script type="text/javascript" src="resources/js/getLocation.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <title>와이파이 정보 구하기</title>
    </head>
    <body>
        <h1>북마크 그룹 수정</h1>
        <a href="http://localhost:8080">홈</a> |
        <a href="http://localhost:8080/history">위치 히스토리 목록</a> |
        <a href="confirm.jsp">OPEN API 와이파이 정보 가져오기</a>
        <a href="http://localhost:8080/bookmarkList">북마크 보기</a> |
        <a href="http://localhost:8080/bookmarkGroupList">북마크 그룹 관리</a>
        <br><br>

        <form action="bookmarkGroupEdit" method="post">
            <table id="wifi">
                <tbody>
                <tr>
                    <td class="column">북마크 ID</td>
                    <td><input type="text" name="BG_ID" value="${bookmarkGroup.BG_ID}" readonly></td>
                </tr>
                <tr>
                    <td class="column">북마크 이름</td>
                    <td><input type="text" name="BG_NAME" value="${bookmarkGroup.BG_NAME}"></td>
                </tr>
                <tr>
                    <td class="column">순서</td>
                    <td><input type="text" name="BG_ORDER" value="${bookmarkGroup.BG_ORDER}"></td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center; display: block;">
                <a href="../">돌아가기</a> | <button type="submit">수정</button>
            </div>
        </form>


    </body>
</html>
