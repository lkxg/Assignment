<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/test" method="post" enctype="multipart/form-data">
    <label for="file">选择文件:</label>
    <input type="file" name="file" id="file"><br><br>
    <input type="submit" name="submit" value="上传">
</form>
<img src="${imgurl}" alt="img">
<p>${msg}</p>
</body>
</html>