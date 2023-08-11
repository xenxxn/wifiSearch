# 파일 설명서

**src – main – java 하위에 대한 설명**

1. DatabaseConnector – 데이터 베이스 연결 작업
2. WifiAPI – open API 연동 작업
3. Model 폴더 – 데이터베이스 테이블 클래스
4. Service 폴더 – DB 연결을 통해 각 기능을 수행
5. Servlet 폴더 – CRUD 페이지와 연결

---------------------------------------------------------------

**src – main – webapp 하위에 대한 설명**

1. resources 폴더 – CSS와 JavaScript 파일이 담겨 있는 폴더
2. jsp 폴더 – 기능 별 화면
3. index: 메인 페이지
4. info: WiFi 상세 정보 페이지 (북마크 추가 기능)
5. confirm: Open API 정보 가져오기 버튼을 눌렀을 때 총 몇 개의 정보를 가져오는지 보여주는 페이지
6. history: 위치 히스토리 목록 페이지
7. bookmarkList: 북마크 보기 페이지
8. bookmarkDelete: 북마크 삭제 페이지, 해당 북마크 정보를 보여주며 삭제 여부에 대해 확인 후 기능 수행
9. bookmarkGroupList: 북마크 그룹 관리 페이지
10. bookmarkGroupAdd: 북마크 그룹 추가 페이지
11. bookmarkGroupEdit: 북마크 그룹 수정 페이지
12. bookmarkGroupDelete: 북마크 그룹 삭제 페이지, 해당 북마크 그룹 정보를 보여주며 삭제 여부에 대해 확인 후 기능 수행