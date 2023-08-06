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
function sendLocation() {
    var latitude = parseFloat($("#latitude").val());
    var longitude = parseFloat($("#longitude").val());
    var baseUrl = "http://localhost:8080/";
    $.ajax({
        type: "POST",
        url: baseUrl + "nearByWifiData",
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
// $('#getNearbyWifiBtn').on('click', function () {
//     // 서버로 근처 WiFi 정보 요청
//     $.ajax({
//         url: '/getNearbyWifi', // 서버에서 해당 URL에 대한 요청을 처리하는 메서드 필요
//         method: 'GET',
//         dataType: 'json',
//         success: function (data) {
//             // 요청에 성공하면 서버로부터 받은 데이터를 서버에 INSERT
//             insertNearbyWifiToServer(data); // 응답 데이터를 인자로 전달
//         },
//         error: function () {
//             alert('근처 WiFi 정보를 가져오는데 실패했습니다.');
//         }
//     });
// });
//
// // 서버에 근처 WiFi 정보를 INSERT하는 함수
// function insertNearbyWifiToServer(data) {
//     $.ajax({
//         url: '/insertNearbyWifi', // 서버에서 해당 URL에 대한 INSERT 기능을 구현해야 함
//         method: 'POST',
//         contentType: 'application/json',
//         data: JSON.stringify(data),
//         success: function () {
//             alert('근처 WiFi 정보를 성공적으로 저장했습니다.');
//         },
//         error: function () {
//             alert('근처 WiFi 정보를 저장하는데 실패했습니다.');
//         }
//     });
// }