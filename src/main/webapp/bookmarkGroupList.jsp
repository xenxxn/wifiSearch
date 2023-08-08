<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="resources/css/index.css" type="text/css">
<html>
<head>
    <script type="text/javascript" src="resources/js/getLocation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>와이파이 정보 구하기</title>
</head>
    <body>
        <button type="button" onclick="goToBookmarkGroupAdd()"></button>
        <table id="wifi">
            <thead>
            <tr>
                <th>ID</th>
                <th>북마크이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody id="wifi_Tbody">
                <c:forEach items="${bookmarkList}" var="bookmarkList">
                    <tr>
                        <td>${bookmarkList.BG_ID}</td>
                        <td>${bookmarkList.BG_NAME}</td>
                        <td>${bookmarkList.BG_ORDER}</td>
                        <td>${bookmarkList.BG_RG_DATE}</td>
                        <td>${bookmarkList.BG_MD_DATE}</td>
                        <td><a href="bookmarkGroupEdit?BG_ID=${bookmarkList.BG_ID}">수정</a><a href="bookmarkGroupDelete?BG_ID=${bookmarkList.BG_ID}">삭제</a></td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
<script>
    function goToBookmarkGroupAdd() {
        window.location.href = "bookmarkGroupAdd.jsp"; // 페이지 이동
    }
</script>