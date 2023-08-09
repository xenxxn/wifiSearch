<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="resources/css/info.css" type="text/css">
<html>
<head>
    <script type="text/javascript" src="resources/js/getLocation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>와이파이 정보 구하기</title>
</head>
    <body>
    <h1>북마크 삭제</h1>
    <a href="http://localhost:8080">홈</a> |
    <a href="http://localhost:8080/history">위치 히스토리 목록</a> |
    <a href="confirm.jsp">OPEN API 와이파이 정보 가져오기</a>
    <a href="http://localhost:8080/bookmarkList">북마크 보기</a> |
    <a href="http://localhost:8080/bookmarkGroupList">북마크 그룹 관리</a>
    <br><br>
        <form action="bookmarkDelete" method="post" onsubmit="return confirmDelete();">
            <input type="hidden" name="BM_ID" value="${bookmark.BM_ID}">
            <table id="wifi">
                <tbody>
                <tr>
                    <td class="column">북마크 ID</td>
                    <td><input type="text" name="BG_BM_NAME" value="${bookmark.BG_BM_NAME}" readonly></td>
                </tr>
                <tr>
                    <td class="column">북마크 이름</td>
                    <td><input type="text" name="WF_BM_NAME" value="${bookmark.WF_BM_NAME}" readonly></td>
                </tr>
                <tr>
                    <td class="column">등록일자</td>
                    <td><input type="text" name="BM_RG_DATE" value="${bookmark.BM_RG_DATE}" readonly></td>
                </tr>
                </tbody>
            </table>
            <br>
            <div style="text-align: center; display: block;">
                <a href="../">돌아가기</a> | <button type="submit">삭제</button>
            </div>
        </form>
    </body>
</html>
<script>
    function confirmDelete() {
        if (confirm("정말로 삭제하시겠습니까?")) {
            alert("북마크 삭제가 완료되었습니다.")
            return true;
        } else {
            return false;
        }
    }
</script>