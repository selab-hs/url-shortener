<%--
  Created by IntelliJ IDEA.
  User: harimhwang
  Date: 2024. 6. 19.
  Time: 오후 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>url shortener</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>
<body>
<%--<jsp:include page="menu.jsp"/>--%>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">hello shortener</h1>
    </div>
</div>
<div class="container" align="center">
    <div class="col-md-4 col-me-offset-4">
        <h3 class="form-signin-heading">URL Shortener</h3>
<%--        <%--%>
<%--            String error = request.getParameter("error");--%>
<%--            if(error!=null){--%>
<%--                out.print("<div class='alert alert-danger'>");--%>
<%--                out.print("아이디와 비밀번호를 확인해주세요.");--%>
<%--                out.print("</div>");--%>
<%--            }--%>
<%--        %>--%>
        <form class="form-signin" action="/shortener" method="post">
            <div class="form-group">
                <label class="sr-only">origin url</label>
                <input name="origin url" class="form-control" placeholder="origin url" required autofocus>
            </div>
            <div>
                <button class="btn btn-lg btn-success btn-block" type="submit">shortener</button>
            </div>
        </form>
        <br/>

        <div class="form-group">

    <c:if test="${not empty shortUrl}">
        <div class="alert alert-success mt-4">
            <a class="form-control" href="${shortUrl}" placeholder="Click here to go to your shortened " target="_blank">URL:${shortUrl}</a>
        </div>
    </c:if>
        </div>

    </div>
</div>
</body>
</html>