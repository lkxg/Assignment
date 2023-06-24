<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
  <script src="https://kit.fontawesome.com/b0d3af6dfc.js" crossorigin="anonymous"></script>
  <script src="https://blog-static.cnblogs.com/files/axqa/emojiCursor.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/head.js"></script>
  <style>
    @font-face {
      font-family:'test';
      src: url("${pageContext.request.contextPath}/font/hanyisunshangxiangjianti.ttf");
    }
    *{
      font-family: 'test', 'PingFang SC', sans-serif;
    }
  </style>
</head>
<body>
<div class="navbar" style="display: flex;">
  <a href="${pageContext.request.contextPath}/main"><img width="22" height="22" src="${pageContext.request.contextPath}/image/home.png" alt="home"/>首页</a>
  <a href="${pageContext.request.contextPath}/news?newsType=1"><img width="22" height="22" src="${pageContext.request.contextPath}/image/dancing-party.png" alt="dancing-party"/>娱乐</a>
  <a href="${pageContext.request.contextPath}/news?newsType=2"><img width="22" height="22" src="${pageContext.request.contextPath}/image/basketball.png" alt="basketball"/><span style="text-align: center;">体育</span></a>
  <a href="${pageContext.request.contextPath}/news?newsType=3"><img width="22" height="22" src="${pageContext.request.contextPath}/image/smart-home-automation.png" alt="smart-home-automation"/>科技</a>
  <a href="${pageContext.request.contextPath}/news?newsType=4"><img width="22" height="22" src="${pageContext.request.contextPath}/image/3d-glasses.png" alt="3d-glasses"/>电影</a>
  <a href="${pageContext.request.contextPath}/news?newsType=5"><img width="22" height="22" src="${pageContext.request.contextPath}/image/ds3-tool.png" alt="ds3-tool"/>游戏</a>
  <a href="${pageContext.request.contextPath}/news?newsType=6"><img width="22" height="22" src="${pageContext.request.contextPath}/image/android-app-drawer.png" alt="android-app-drawer"/>软件</a>
  <a href="${pageContext.request.contextPath}/news?newsType=7" style="margin-right: 0px;"><img width="22" height="22" src="${pageContext.request.contextPath}/image/connection-status-off--v1.png" alt="connection-status-off--v1"/>其他</a>
  <form action="${pageContext.request.contextPath}/search" class="search-bar" style="display: flex;padding-top: 20px;justify-content: flex-end;width: 300px;margin-left: 0px;">
    <input type="search" name="searchTitle" pattern=".*\S.*" required style="margin-bottom: 20px;font-size: 16px;">
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
      request.getSession().setAttribute("avatarValue", avatarValue);
    }
    if (username && password) {
  %>
  <div class="dropdown">
    <button class="dropbtn" onclick="myFunction()" style="padding-top: 15px;padding-bottom: 15px;">
      <img src="${cookie.avatar.value}"  class="avatar" style="width: 50px ; height: 50px" >
      <%=usernameValue%>
      <i class="fa-solid fa-angle-down"></i>
    </button>
    <div class="dropdown-content" id="myDropdown">
      <a href="info">个人资料</a>
<%
        if (userType.equals("3")){
%>
        <a href="/usermanage">用户管理</a>
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
