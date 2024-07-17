$(document).ready(function () {
    if (window.localStorage.getItem("X-READYS-AUTH-TOKEN") == null) {
        location.href = "https://readys.link/main";
    }

    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');
    $.ajax({
        url: '/api/v1/shorts',
        type: 'GET',
        headers: {
            'X-READYS-AUTH-TOKEN': authToken // Set Authorization header
        },
        dataType: 'json',
        success: function (data) {
            let tableBody = $('#shortcodeTableBody');
            data.data.forEach(function (item) {
                let row = $('<tr></tr>');
                row.append(`<td><a href="/history/${item.shortcode}">${item.shortcode}</a></td>`);
                row.append(`<td>${item.view}</td>`);
                tableBody.append(row);
            });
        },
        error: function (xhr, status, error) {
            console.error('Error fetching data: ', error);
            alert('Failed to fetch data. Check console for more details.');
        }
    });
});