<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>URL Shortener</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function() {
            // 폼 제출 이벤트를 처리
            $('.form-signin').submit(function(event) {
                event.preventDefault(); // 폼 기본 제출 막기
                var originUrl = $('input[name="origin url"]').val(); // 입력된 원본 URL 가져오기

                // AJAX 요청을 보내기
                $.ajax({
                    url: '/api/v1/short', // API 경로
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ "originUrl": originUrl }), // 요청 JSON 형태로 만들기
                    success: function(response) {
                        // 응답 처리: 단축 URL을 링크로 표시
                        if(response.data) {
                            $('.alert').remove();
                            var alertSuccess = '<div class="alert alert-success mt-4">' +
                                '<a class="form-control" href="/' + response.data.replace("http://localhost:8080/", "") + '" target="_blank">URL: ' + response.data + '</a>' +
                                '</div>';
                            $('.form-group').after(alertSuccess);
                        }
                    },
                    error: function() {
                        // 오류 처리
                        $('.alert').remove();
                        var alertError = '<div class="alert alert-danger mt-4">Error creating short URL.</div>';
                        $('.form-group').after(alertError);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container" align="center">
    <div class="col-md-4 col-me-offset-4">
        <h3 class="form-signin-heading">URL Shortener</h3>
        <form class="form-signin" action="/shortener" method="post">
            <div class="form-group">
                <label class="sr-only">origin url</label>
                <input type="text" name="origin url" class="form-control" placeholder="origin url" required autofocus>
            </div>
            <button type="submit" class="btn btn-lg btn-success btn-block">Shorten URL</button>
        </form>
    </div>
</div>
</body>
</html>
