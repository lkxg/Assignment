<%--
  Created by IntelliJ IDEA.
  User: Kaixl
  Date: 2023/6/3
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search</title>
  <link rel="stylesheet" href="/css/search.css">
</head>
<body>
<jsp:include page="head.jsp"/>
<form action="" class="search-bar">
  <input type="search" name="search" pattern=".*\S.*" required>
  <button class="search-btn" type="submit">
    <span>Search</span>
  </button>
</form>
</body>
</html>
