$(document).ready(function () {
    if (window.localStorage.getItem("X-READYS-AUTH-TOKEN") == null) {
        location.href = "http://localhost:8080/main";
    }

    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');
    const shorturl = window.location.href.split("/");
    console.log('/api/v1/system-action-logs/' + shorturl[shorturl.length - 1]);

    $.ajax({
        url: '/api/v1/system-action-logs/' + shorturl[shorturl.length - 1],
        type: 'GET',
        headers: {
            'X-READYS-AUTH-TOKEN': authToken // Set Authorization header
        },
        dataType: 'json',
        success: function (data) {
            let tableBody = $('#shortcodeLogTable');
            data.data.forEach(function (item) {
                let row = $('<tr></tr>');
                row.append(`<td>${item.ipAddress}</td>`);
                row.append(`<td>${item.method}</td>`);
                row.append(`<td>${item.path}</td>`);
                row.append(`<td>${item.userAgent}</td>`);
                row.append(`<td>${item.host}</td>`);
                row.append(`<td>${item.referer}</td>`);
                tableBody.append(row);
            });
        },
        error: function (xhr, status, error) {
            console.error('Error fetching data: ', error);
            alert('Failed to fetch data. Check console for more details.');
        }
    });
});