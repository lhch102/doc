<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>template</title>
    <script src="${pageContext.request.contextPath}/jquery-1.9.1.min.js" type="text/javascript"></script>
</head>

<body>
<H1>rms-account</H1>
<span>验证码：</span>
<script type="application/javascript">
    $(document).ready(function () {
        $("button").click(function () {
            $.get("/servlet/validateCodeServlet", function (data, status) {
                alert("数据：" + data + "\n状态：" + status);
            });
        });
    });
</script>

<form action="/accountLogin/login.json">
    usrName:<input id="userName" name="userName"/>
    password:<input id="password" name="password"/>
    <input type="button" value="登录"/>
</form>
</body>
</html>
