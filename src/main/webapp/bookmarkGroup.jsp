<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="resources/css/table.css" type="text/css">
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
<br>

LAT : <input type="text" id="latitude" name="latitude"> ,
LNT : <input type="text" id="longitude" name="longitude">
<button id="getLocationBtn">내 위치 가져오기</button>
<%--    아이디는 태그의 변수명(고유 key값), onclick=버튼 눌렀을때의 이벤트 --%>
<button type="button" id="btn_getWifiData">근처 wifi 정보 가져오기</button>

<table id="wifi">
    <thead>
    <tr>
        <th>거리</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI 접속 환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody id="wifi_Tbody">
    <tr>
        <td colspan="16">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    </tbody>
</table>
</body>
</html>
