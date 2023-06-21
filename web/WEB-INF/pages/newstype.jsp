<%@ page import="java.util.List" %>
<%@ page import="me.kaixuan.entity.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新闻</title>
    <style>
        body {
            background-image: url("https://www.helloimg.com/images/2023/06/13/oshdvE.png");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }
        .news {
            opacity: 0.9;
            width: 32%;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            padding: 20px;
            box-sizing: border-box;
            transition: all 0.3s ease-in-out;
        }
        .news:hover {
            transform: translateY(-5px);
            box-shadow: 0px 5px 20px rgba(0,0,0,0.2);
        }
        .news img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }
        .news h2 {
            font-size: 24px;
            margin-bottom: 10px;
            color: #333;
        }
        .news p {
            font-size: 16px;
            line-height: 1.5;
            color: #666;
        }
        .news a {
            display: block;
            margin-top: 10px;
            font-size: 16px;
            color: #007bff;
            text-decoration: none;
        }
        .news .author {
            font-size: 12px;
            color: #999;
            margin-top: 10px;
        }
        .news .time {
            font-size: 12px;
            color: #999;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container">
    <% List<News> news = (List<News>) request.getAttribute("news");
        if (news != null) {
            for (News n : news) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String pTime = sdf.format(n.getPublishTime());
    %>
    <div class="news">
        <a href="shownews?newsId=<%=n.getnId()%>">
            <img src=<%=n.getCover()%>>
            <h2><%=n.getTitle()%></h2>
            <p><%=n.getAbstracts()%></p>
            <span class="origin">作者：<%=n.getUser().getUsername()%></span>&emsp;&emsp;&emsp;&emsp;&emsp;
            <span class="author">来源：<%=n.getReprint().equals("")?"原创":n.getReprint()%></span><br><br>
            <span class="time">发布时间：<%=pTime%></span>
        </a>
    </div>
    <% }
        } %>
</div>
</body>
</html>