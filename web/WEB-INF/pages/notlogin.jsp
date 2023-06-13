<%--
  Created by IntelliJ IDEA.
  User: Kaixl
  Date: 2023/6/12
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>未登录</title>
    <style>
        body {
            background-image: url(image/errorimg.jpg);
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
            color: #fff;
            margin: 0;
            padding: 0;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.5);
        }
        h1 {
            font-size: 48px;
            margin: 0;
            margin-bottom: 20px;
            text-align: center;
        }
        p {
            font-size: 24px;
            margin: 0;
            margin-bottom: 40px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            font-size: 18px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Oops!</h1>
    <p>您还未登录，请登录后再试。</p>
    <a href="/reg" class="btn">去登录</a>
</div>
</body>
</html>