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

<script>
    $('#getLocationBtn').on('click', function (event){
        getMyLocation();
    });

    $('#btn_getWifiData').on('click', function (){
        var latitude = parseFloat($("#latitude").val());
        var longitude = parseFloat($("#longitude").val());
        var baseUrl = "http://localhost:8080/";

        if(isNaN(latitude) || isNaN(longitude)) {
            alert("위치 정보를 입력해 주세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: baseUrl + "nearByWifiData",
            data: {
                latitude: latitude,
                longitude: longitude
            },
            success: function(data) {
                console.log(data);
                const wifi_Tbody = document.getElementById('wifi_Tbody');
                $("#wifi_Tbody tr").remove(); //행 전체삭제
                if(data.length > 0){
                    for(let i = 0; i < data.length; i++){
                        const newRow0 = wifi_Tbody.insertRow();
                        const newCell0 = newRow0.insertCell();
                        const newText0 = document.createTextNode("NULL");
                        newCell0.appendChild(newText0);
                        const newCell1 = newRow0.insertCell();
                        const newText1 = document.createTextNode(data[i].WF_ID);
                        newCell1.appendChild(newText1);
                        const newCell2 = newRow0.insertCell();
                        const newText2 = document.createTextNode(data[i].WF_BOROUGH);
                        newCell2.appendChild(newText2);
                        const newCell3 = newRow0.insertCell();
                        const newText3 = document.createTextNode(data[i].WF_NAME);
                        newCell3.appendChild(newText3);
                        const newCell4 = newRow0.insertCell();
                        const newText4 = document.createTextNode(data[i].WF_ST_ADDR);
                        newCell4.appendChild(newText4);
                        const newCell5 = newRow0.insertCell();
                        const newText5 = document.createTextNode(data[i].WF_DT_ADDR);
                        newCell5.appendChild(newText5);
                        const newCell6 = newRow0.insertCell();
                        const newText6 = document.createTextNode(data[i].WF_FLOOR);
                        newCell6.appendChild(newText6);
                        const newCell7 = newRow0.insertCell();
                        const newText7 = document.createTextNode(data[i].WF_INST_TYPE);
                        newCell7.appendChild(newText7);
                        const newCell8 = newRow0.insertCell();
                        const newText8 = document.createTextNode(data[i].WF_INST_ORGN);
                        newCell8.appendChild(newText8);
                        const newCell9 = newRow0.insertCell();
                        const newText9 = document.createTextNode(data[i].WF_SERVICE);
                        newCell9.appendChild(newText9);
                        const newCell10 = newRow0.insertCell();
                        const newText10 = document.createTextNode(data[i].WF_NT_TYPE);
                        newCell10.appendChild(newText10);
                        const newCell11 = newRow0.insertCell();
                        const newText11 = document.createTextNode(data[i].WF_YEAR);
                        newCell11.appendChild(newText11);
                        const newCell12 = newRow0.insertCell();
                        const newText12 = document.createTextNode(data[i].WF_INOUT);
                        newCell12.appendChild(newText12);
                        const newCell13 = newRow0.insertCell();
                        const newText13 = document.createTextNode(data[i].WF_ENVIRONMENT);
                        newCell13.appendChild(newText13);
                        const newCell14 = newRow0.insertCell();
                        const newText14 = document.createTextNode(data[i].WF_Y);
                        newCell14.appendChild(newText14);
                        const newCell15 = newRow0.insertCell();
                        const newText15 = document.createTextNode(data[i].WF_X);
                        newCell15.appendChild(newText15);
                        const newCell16 = newRow0.insertCell();
                        const newText16 = document.createTextNode(data[i].WF_WORK_DATE);
                        newCell16.appendChild(newText16);
                    }
                }else{

                }
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
</script>