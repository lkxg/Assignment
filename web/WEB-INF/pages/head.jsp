<%@ page import="me.kaixuan.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
  <link rel="stylesheet" href="/css/head.css">
  <script type="text/javascript" src="/js/head.js"></script>
</head>
<body>
<div class="navbar" style="display: flex;">
  <a href="main">首页</a>
  <a href="yule">娱乐</a>
  <a href="tiyu">体育</a>
  <a href="keji">科技</a>
  <a href="dianying">电影</a>
  <a href="youxi">游戏</a>
  <a href="ruanjian">软件</a>
  <a href="qita" style="margin-right: 0px;">其他</a>
  <form action="/search" class="search-bar" style="display: flex;padding-top: 20px;justify-content: flex-end;">
    <input type="search" name="search" pattern=".*\S.*" required style="margin-bottom: 20px;font-size: 16px;">
    <button class="search-btn" type="submit">
      <span>Search</span>
    </button>
  </form>
  <%
    Cookie cookie = null; //创建cookie对象
    Cookie[] cookies = null;
    boolean username= false;
    boolean password = false;
    String passwordValue = null;
    String usernameValue = null;
    String avatarValue = null;
    String userType = null;
    // 获取 cookie 的数据
    cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i];
        if ((cookie.getName()).equals("username")) {
          username = true;
          usernameValue = cookie.getValue();
        }
        if ((cookie.getName()).equals("password")) {
          password = true;
            passwordValue = cookie.getValue();
        }
        if ((cookie.getName()).equals("avatar")) {
          avatarValue = cookie.getValue();
        }
        if ((cookie.getName()).equals("usertype")) {
          userType = cookie.getValue();
        }
      }
    }
    if (usernameValue != null&& passwordValue != null) {
      request.getSession().setAttribute("username", usernameValue);
      request.getSession().setAttribute("password", passwordValue);
    }
    if (username && password) {
  %>
  <div class="dropdown">
    <button class="dropbtn" onclick="myFunction()">
      <img src= "<%=avatarValue%>" class="avatar">
      <%=usernameValue%>
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content" id="myDropdown">
      <a href="info">个人资料</a>
<%
        if (userType.equals("3")){
%>
        <a href="/usermanage">用户管理</a>
        <a href="/newsmanage">新闻管理</a>
        <a href="/check">新闻审核</a>
        <a href="/release">新闻发布</a>
        <a href="/mynews">我的新闻</a>
        <a href="logout">退出登录</a>
<%
          }
        else{
%>

      <a href="/release">新闻发布</a>
      <a href="/mynews">我的新闻</a>
      <a href="logout">退出登录</a>
<%
        }
%>
    </div>
  </div>
</div>
<%
} else {
%>
<a href="reg" style="float: right"><img src= "https://www.helloimg.com/images/2023/06/05/osBiU9.jpg" class="avatar">&nbsp;登录/注册</a>
<%
  }
%>
</div>
</body>
</html>
