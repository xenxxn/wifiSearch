package com.zen.wifisearch.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wifi {
    private String WF_ID; //관리번호
    private String WF_BOROUGH; //자치구
    private String WF_NAME; //와이파이명
    private String WF_ST_ADDR; //도로명주소
    private String WF_DT_ADDR; //상세주소
    private String WF_FLOOR; //설치위치
    private String WF_INST_TYPE; //설치유형
    private String WF_INST_ORGN; //설치기관
    private String WF_SERVICE; //서비스구분
    private String WF_NT_TYPE; //망종류
    private int WF_YEAR; //설치년도
    private String WF_INOUT; //실내외구분
    private String WF_ENVIRONMENT; //접속환경
    private double WF_X; //x좌표 경도 LNT
    private double WF_Y;//y좌표 위도 LAT
    private String WF_WORK_DATE;//작업일자
    private double distance; // 거리구하기 위한 변수
}
