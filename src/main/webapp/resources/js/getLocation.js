function getMyLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        // 브라우저가 위치 정보를 지원하지 않는 경우 처리
        alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
    }
}

// 위치 정보 가져오기 성공 시 실행되는 함수
function showPosition(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;

    // 위도와 경도를 각각의 input 요소에 입력
    $('#latitude').val(latitude);
    $('#longitude').val(longitude);

    // 근처 WiFi 정보 가져오기 버튼 활성화
    $('#getNearbyWifiBtn').prop('disabled', false);

    sendLocation(latitude, longitude);
}

// 위치 정보 가져오기 실패 시 실행되는 함수
function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            alert("사용자가 위치 정보 제공에 동의하지 않았습니다.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("위치 정보를 사용할 수 없습니다.");
            break;
        case error.TIMEOUT:
            alert("위치 정보 가져오기 시간이 초과되었습니다.");
            break;
        case error.UNKNOWN_ERROR:
            alert("알 수 없는 오류가 발생했습니다.");
            break;
    }
}
function sendLocation(latitude, longitude) {
    var baseUrl = "http://localhost:8080/";
    $.ajax({
        type: "POST",
        url: baseUrl + "historyAdd",
        data: {
            latitude: latitude,
            longitude: longitude
        },
        success: function(data) {
            console.log(data);
        },
        error: function(error) {
            console.error(error);
        }
    });
}
