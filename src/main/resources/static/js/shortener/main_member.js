document.addEventListener("DOMContentLoaded", function () {
    if (window.localStorage.getItem("X-READYS-AUTH-TOKEN") == null) {
        location.href = "http://localhost:8080/main";
    }
});

// $(document).ready(function () {
//     $("#header").load("/templates/header/header.html");
//     $("#sidebar").load("/templates/sidebar/sidebar.html");
// });

// UUID 생성 함수
function generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

// 쿠키 값을 가져오는 함수
function getCookie(name) {
    let value = `; ${document.cookie}`;
    let parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
        return parts.pop().split(';').shift();
    }
}

// 쿠키를 설정하는 함수
function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

// 클라이언트 ID를 쿠키에서 가져오거나 없으면 생성하여 설정하는 함수
function getClientIdFromCookie() {
    let clientId = getCookie("client-id");
    if (!clientId) {
        clientId = createClientIdFromCookie();
    }
    return clientId;
}

// UUID를 생성하여 쿠키에 설정하는 함수
function createClientIdFromCookie() {
    const clientId = generateUUID();
    setCookie("client-id", clientId, 183); // 쿠키 유효기간 183일 설정
    return clientId;
}

$('.form-signin').submit(function (event) {
    event.preventDefault();
    const originUrl = $('input[name="originUrl"]').val();
    const clientId = getClientIdFromCookie();
    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');

    $.ajax({
        url: '/api/v1/short',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'client-id': clientId,
            'X-READYS-AUTH-TOKEN': authToken
        },
        data: JSON.stringify({"originUrl": originUrl}),
        success: function (response) {
            $('.alert').remove();
            const alertSuccess = '<div class="alert alert-success mt-4">' +
                '<a class="form-control" href="/' + response.data.replace("http://localhost:8080/", "") + '" target="_blank">URL: ' + response.data + '</a>' +
                '</div>';
            $('.form-group').after(alertSuccess);
        },
        error: function (response) {
            $('.alert').remove();
            const alertError = '<div class="alert alert-danger mt-4">' + JSON.parse(response.responseText).reason + '</div>';
            $('.form-group').after(alertError);
        }
    });
});


window.onload = function () {
    // 로컬 스토리지에서 토큰 확인
    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');

    // 로그인 버튼과 로그아웃 버튼 요소 가져오기
    const loginButton = document.getElementById('loginButton');
    const signUp = document.getElementById('signUp');
    const logoutButton = document.getElementById('logoutButton');

    // 토큰 유무에 따라 버튼 표시 조정
    if (authToken !== null && authToken !== "") {
        loginButton.style.display = 'none';
        signUp.style.display = 'none'
        logoutButton.style.display = 'block';
    } else {
        loginButton.style.display = 'block';
        signUp.style.display = 'block'
        logoutButton.style.display = 'none';
    }
};

function logout() {
    // 로컬 스토리지에서 토큰 삭제
    localStorage.removeItem('X-READYS-AUTH-TOKEN');
    // 페이지 새로고침
}