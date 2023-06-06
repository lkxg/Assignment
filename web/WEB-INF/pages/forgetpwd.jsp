<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>忘记密码</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/forgetpwd.css">
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container">
    <form action="/fgpwd">
        <label for="email">请输入您的电子邮件地址：</label>
        <input type="email" id="email" name="email" required>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <p style="color: red;"><%=msg%></p>
        <%}%>
        <button type="submit">发送身份验证信息</button>
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
        <a href="/reg">返回登录</a>
    </form>
</div>
</body>
</html>