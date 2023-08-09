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
        <h1>와이파이 정보 구하기</h1>
        <a href="http://localhost:8080">홈</a> |
        <a href="http://localhost:8080/history">위치 히스토리 목록</a> |
        <a href="confirm.jsp">OPEN API 와이파이 정보 가져오기</a>
        <div id = "additionalLinks">
            <a href="http://localhost:8080/bookmarkList">북마크 보기</a> |
            <a href="http://localhost:8080/bookmarkGroupList">북마크 그룹 관리</a>
        </div>
        <br>
            LAT : <input type="text" id="latitude" name="latitude"> ,
            LNT : <input type="text" id="longitude" name="longitude">
            <button id="getLocationBtn">내 위치 가져오기</button>
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
        var longitude = parseFloat($("#longitude").val());
        var latitude = parseFloat($("#latitude").val());
        var baseUrl = "http://localhost:8080/";

        if(isNaN(latitude) || isNaN(longitude)) {
            alert("위치 정보를 입력해 주세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: baseUrl + "nearByWifiData",
            data: {
                longitude: longitude,
                latitude: latitude
            },
            success: function(data) {
                console.log(data);
                const wifi_Tbody = document.getElementById('wifi_Tbody');
                $("#wifi_Tbody tr").remove(); //행 전체삭제
                if(data.length > 0){
                    for(let i = 0; i < data.length; i++){
                        const newRow0 = wifi_Tbody.insertRow();

                        const distance_tr = newRow0.insertCell();
                        const distance_td = document.createTextNode(data[i].distance);
                        distance_tr.appendChild(distance_td);

                        const wifi_id_tr = newRow0.insertCell();
                        const wifi_id_td = document.createTextNode(data[i].WF_ID);
                        wifi_id_tr.appendChild(wifi_id_td);

                        const wifi_borough_tr = newRow0.insertCell();
                        const wifi_borough_td = document.createTextNode(data[i].WF_BOROUGH);
                        wifi_borough_tr.appendChild(wifi_borough_td);

                        //동적테이블에 해당 와이파이 info.jsp로 이동하는 링크 걸기
                        const wifi_name_tr = newRow0.insertCell();
                        const wifi_name_link = document.createElement("a");
                        const wifi_id = data[i].WF_ID;
                        wifi_name_link.href = "http://localhost:8080/info?wifi_id=" + wifi_id; // wifi_id를 쿼리 파라미터로 추가
                        wifi_name_link.textContent = data[i].WF_NAME;
                        wifi_name_tr.appendChild(wifi_name_link);

                        const wifi_addr1_tr = newRow0.insertCell();
                        const wifi_addr1_td = document.createTextNode(data[i].WF_ST_ADDR);
                        wifi_addr1_tr.appendChild(wifi_addr1_td);

                        const wifi_addr2_tr = newRow0.insertCell();
                        const wifi_addr2_td = document.createTextNode(data[i].WF_DT_ADDR);
                        wifi_addr2_tr.appendChild(wifi_addr2_td);

                        const wifi_floor_tr = newRow0.insertCell();
                        const wifi_floor_td = document.createTextNode(data[i].WF_FLOOR);
                        wifi_floor_tr.appendChild(wifi_floor_td);

                        const wifi_inst_type_tr = newRow0.insertCell();
                        const wifi_inst_type_td = document.createTextNode(data[i].WF_INST_TYPE);
                        wifi_inst_type_tr.appendChild(wifi_inst_type_td);

                        const wifi_inst_orgn_tr = newRow0.insertCell();
                        const wifi_inst_orgn_td = document.createTextNode(data[i].WF_INST_ORGN);
                        wifi_inst_orgn_tr.appendChild(wifi_inst_orgn_td);

                        const wifi_service_tr = newRow0.insertCell();
                        const wifi_service_td = document.createTextNode(data[i].WF_SERVICE);
                        wifi_service_tr.appendChild(wifi_service_td);

                        const wifi_nt_type_tr = newRow0.insertCell();
                        const wifi_nt_type_td = document.createTextNode(data[i].WF_NT_TYPE);
                        wifi_nt_type_tr.appendChild(wifi_nt_type_td);

                        const wifi_year_tr = newRow0.insertCell();
                        const wifi_year_td = document.createTextNode(data[i].WF_YEAR);
                        wifi_year_tr.appendChild(wifi_year_td);

                        const wifi_inout_tr = newRow0.insertCell();
                        const wifi_inout_td = document.createTextNode(data[i].WF_INOUT);
                        wifi_inout_tr.appendChild(wifi_inout_td);

                        const wifi_environment_tr = newRow0.insertCell();
                        const wifi_environment_td = document.createTextNode(data[i].WF_ENVIRONMENT);
                        wifi_environment_tr.appendChild(wifi_environment_td);

                        const wifi_x_tr = newRow0.insertCell();
                        const wifi_x_td = document.createTextNode(data[i].WF_X);
                        wifi_x_tr.appendChild(wifi_x_td);

                        const wifi_y_tr = newRow0.insertCell();
                        const wifi_y_td = document.createTextNode(data[i].WF_Y);
                        wifi_y_tr.appendChild(wifi_y_td);

                        const wifi_work_date_tr = newRow0.insertCell();
                        const wifi_work_date_td = document.createTextNode(data[i].WF_WORK_DATE);
                        wifi_work_date_tr.appendChild(wifi_work_date_td);
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
<style>
    #additionalLinks {
        display: none;
    }
</style>
<script>
    // JavaScript 코드
    const getWifiDataButton = document.getElementById('btn_getWifiData');
    const additionalLinksDiv = document.getElementById('additionalLinks');

    getWifiDataButton.addEventListener('click', function() {
        additionalLinksDiv.style.display = 'block';
    });
</script>