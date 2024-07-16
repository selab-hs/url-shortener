$(document).ready(function () {
    const authToken = localStorage.getItem('X-READYS-AUTH-TOKEN');
    $.ajax({
        url: '/api/v1/shorts',
        type: 'GET',
        headers: {
            'X-READYS-AUTH-TOKEN': authToken// Set Authorization header
        },
        dataType: 'json',
        success: function (data) {
            let tableBody = $('#shortcodeTableBody');
            data.data.forEach(function (item) {
                let row = $('<tr></tr>');
                //  '<a class="form-control" href="/' + response.data.replace("http://localhost:8080/", "") + '" target="_blank">URL: ' + response.data + '</a>' +
                row.append(`<td><a href="/history/${item.shortcode}'"> ${item.shortcode}></a></td>`);
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
