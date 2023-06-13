<%@ page import="java.util.List" %>
<%@ page import="me.kaixuan.entity.News" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的新闻</title>
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
            background-color: #6c7a89;
            color: #fff;
        }
        .approve {;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            padding: 10px 30px;
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
        <table>
            <thead>
            <tr>
                <th>新闻标题</th>
                <th>新闻摘要</th>
                <th>发布时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <% List<News> news = (List<News>) request.getAttribute("myNews");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (News n : news) {
                    String status = "";
                    if (n.getStatusId() == 1) {
                        status = "待审核";
                    } else if (n.getStatusId() == 2) {
                        status = "审核通过";
                    } else if (n.getStatusId() == 3) {
                        status = "审核未通过";
                    }
                    String pTime = sdf.format(n.getPublishTime());
                    if (n.getStatusId() == 2||n.getStatusId() == 3||n.getStatusId() == 1) {
            %>
            <tr>
                <td><%=n.getTitle()%></td>
                <td><%=n.getAbstracts()%></td>
                <td><%=pTime%></td>
                <td><%=status%></td>
                <td>
                    <button class="approve"  name="detail" onclick="form1.action='/shownews?newsId=<%=n.getnId()%>';form1.submit();" >查看</button>
                    &emsp;
                    <button class="approve" name="delete" style="background-color: #cc0000;" onclick="form1.action='/deletenews?newsId=<%=n.getnId()%>';form1.submit();" >删除</button>
                </td>
            </tr>
            <% }
            } %>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>