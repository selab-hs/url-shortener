function copyToClipboard(text) {
    const tempInput = document.createElement('input');
    tempInput.value = text;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand('copy');
    document.body.removeChild(tempInput);
}

$(document).ready(function () {
    if (window.localStorage.getItem("X-READYS-AUTH-TOKEN") != null) {
        location.href = "/member_home";
    }

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

        $.ajax({
            url: '/api/v1/shorts',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                'client-id': clientId
            },
            data: JSON.stringify({"originUrl": originUrl}),
            success: function (response) {
                $('.alert').remove();
                const alertSuccess = '<div class="alert alert-success mt-4">' +
                    '<a class="form-control" href="/' + response.data.replace("https://readys.link/", "") + '" target="_blank">URL: ' + response.data + '</a>' +
                    '</div>';
                $('.form-group').after(alertSuccess);

                $('#copyButtonContainer').show();
                $('#copyButton').off('click').on('click', function () {
                    copyToClipboard(response.data);
                    alert('URL copied to clipboard');
                });
            },
            error: function (response) {
                $('.alert').remove();
                const alertError = '<div class="alert alert-danger mt-4">' + JSON.parse(response.responseText).reason + '</div>';
                $('.form-group').after(alertError);
                $('#copyButtonContainer').hide();
            }
        });
    });
});