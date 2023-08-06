<%--
  Created by IntelliJ IDEA.
  User: ZEN
  Date: 2023-08-01
  Time: 오후 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <title>Title</title>
    </head>
    <body>
        <h1><%= com.zen.wifisearch.WifiAPI.getTotalCount()%>개의 와이파이 정보를 저장하였습니다.</h1>
        <a href="http://localhost:8080">홈 으로 가기</a>
    </body>
</html>

<script>
    window.onload = function () {
        AjaxTest();
        // AjaxConGet();
    }

    function AjaxConGet(){
        var baseUrl = "http://localhost:8080/";
        $.ajax({
            type:"POST",
            url: baseUrl + "insertWifiData",
            success : function(result){
                console.log("success");
            }
        }).then((result) => {
            console.log("promise");
        }).catch((result) => {
            console.log("error occurred");
        });
    }

    function AjaxTest(){
        var baseUrl = "http://localhost:8080/";
        $.ajax({
            type:"POST",
            url: baseUrl + "deleteWifiData",
            success : function(result){
                console.log("success");
            }
        }).then((result) => {
            console.log("promise");
        }).catch((result) => {
            console.log("error occurred");
        });
    }
</script>