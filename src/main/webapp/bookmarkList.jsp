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
        <h1>북마크 목록</h1>
        <a href="http://localhost:8080">홈</a> |
        <a href="http://localhost:8080">위치 히스토리 목록</a> |
        <a href="confirm.jsp">OPEN API 와이파이 정보 가져오기</a>
        <a href="http://localhost:8080/bookmarkList">북마크 보기</a> |
        <a href="http://localhost:8080/bookmarkGroupList">북마크 그룹 관리</a>
        <table id="wifi">
            <thead>
            <tr>
                <th>ID</th>
                <th>북마크이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody id="wifi_Tbody">
                <c:forEach items="${bookmarkList}" var="bookmarkList">
                    <tr>
                        <td>${bookmarkList.BM_ID}</td>
                        <td>${bookmarkList.BG_BM_NAME}</td>
                        <td>${bookmarkList.WF_BM_NAME}</td>
                        <td>${bookmarkList.BM_RG_DATE}</td>
                        <td><a href="bookmarkDelete?BM_ID=${bookmarkList.BM_ID}">삭제</a></td>
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