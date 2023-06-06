<%@ page import="java.util.List" %>
<%@ page import="me.kaixuan.entity.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻审核</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* 自定义样式 */
        * {
            margin: 0;
            padding: 0;
        }
        table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 8px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #6a5d65;
            color: #fff;
        }
        .approve {
            background-color: #5cb85c;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }
        .reject {
            background-color: #d9534f;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<form action="" method="post" name="form1">
<div class="container" style="padding: 50px 150px;">
    <h2>待审核</h2>
    <table>
        <thead>
        <tr>
            <th>新闻标题</th>
            <th>新闻作者</th>
            <th>新闻内容</th>
            <th>发布时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <% List<News> news = (List<News>) request.getAttribute("news");
            for (News n : news) {
               if (n.getStatusId() == 1) {
            %>
        <tr>
            <td><%=n.getTitle()%></td>
            <td><%=n.getUser().getUsername()%></td>
            <td><a href="#" ><%=n.getAbstracts()%></a></td>
            <td><%=n.getPublishTime()%></td>
            <td>
                <button class="approve" value="<%=n.getnId()%>" name="approve" onclick="form1.action='/checknewsapprove';form1.submit();">通过</button>
                &emsp;&emsp;
                <button class="reject" value="<%=n.getnId()%>" name="reject" onclick="form1.action='/checknewsreject';form1.submit();">拒绝</button>
            </td>
        </tr>
            <% }
            } %>
        </tbody>
    </table>
</div>
</form>
<div class="container" style="padding: 50px 150px;">
    <h2>已审核</h2>
    <table>
        <thead>
        <tr>
            <th>新闻标题</th>
            <th>新闻作者</th>
            <th>新闻内容</th>
            <th>发布时间</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <%
        for (News n : news) {
            String status = n.getStatusId()==2?"通过":"拒绝";
        if (n.getStatusId() == 2||n.getStatusId() == 3) {
        %>
        <tr>
            <td><%=n.getTitle()%></td>
            <td><%=n.getUser().getUsername()%></td>
            <td><a href="#" ><%=n.getAbstracts()%></a></td>
            <td><%=n.getPublishTime()%></td>
            <td><%=status%></td>
        </tr>
        <% }
            } %>
        </tbody>
    </table>
</div>
</body>
</html>