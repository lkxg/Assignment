<%@ page import="java.util.List" %>
<%@ page import="me.kaixuan.entity.User" %>
<%@ page import="com.github.pagehelper.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="/css/usermanage.css">
    <style>
        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }
        .pagination a {
            display: inline-block;
            padding: 10px;
            margin: 0 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-decoration: none;
            color: #333;
        }
        .pagination a.active {
            background-color: #333;
            color: #fff;
        }
        .search1 {
            width: 130px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }

        .search1:focus {
            width: 80%;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container" style="padding-top: 30px;">
    <form name="form1">
    <div class="user-list">
        <input type="text" name="search" placeholder="搜索...输入用户名或邮箱" class="search1">
        <button type="submit" style="background-color: #2d6adf;padding-top: 14px;padding-bottom: 14px;padding-left: 35px;padding-right: 35px;" class="searchbut">搜索</button>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="user-list">
            <% List<User> userList = (List<User>) request.getAttribute("userList");
               List<User> searchList = (List<User>) request.getAttribute("searchList");
            if (searchList!=null) {
                for (User user:searchList) {  %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getEmail()%></td>
                <td>
                    <button type="submit" onclick="form1.method='post';form1.action='/info?userId=<%=user.getId()%>';form1.submit();">详细信息</button>
                    &emsp;
                    <button type="submit" style="background-color: crimson;" onclick="form1.method='post';form1.action='/deleteuser?userId=<%=user.getId()%>';form1.submit();">删除</button>
                </td>
            </tr>
            <% }
            %>
            </tbody>
        </table>
            <% }
               else {
                for (User user:userList) {  %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getEmail()%></td>
                <td>
                    <button type="submit" onclick="form1.method='post';form1.action='/info?userId=<%=user.getId()%>';form1.submit();">详细信息</button>
                    &emsp;
                    <button type="submit" style="background-color: crimson;" onclick="form1.method='post';form1.action='/deleteuser?userId=<%=user.getId()%>';form1.submit();">删除</button>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo"); %>
        <div class="pagination">
            <a href="${pageContext.request.contextPath}/usermanage?pageNum=<%=pageInfo.getPageNum()-1%>" class="prev">上一页</a>
           <%
               if (userList!=null) {
                for (int i=1;i<=pageInfo.getPages();i++) { %>
            <a href="${pageContext.request.contextPath}/usermanage?pageNum=<%=i%>" class="page <%=pageInfo.getPageNum()==i?"active":""%>"><%=i%></a>
            <% } } %>
            <a href="${pageContext.request.contextPath}/usermanage?pageNum=<%=pageInfo.getPageNum()+1%>" class="next">下一页</a>
        </div>
        <%  } %>
    </div>
    </form>
    <div class="user-form" style="margin-left: 100px;">
        <form id="user-form" action="/adduser" method="post">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" style="width: 80%;padding: 10px;margin-bottom: 10px;box-sizing: border-box;border: 2px solid #4CAF50;border-radius: 4px;" required>
            <label for="email">邮箱：</label>
            <input type="email" id="email" name="email" required>
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit">添加</button>
            &emsp;
            <button type="reset" style="background-color: #cc0000;">取消</button>
        </form>
        <% String message = (String) request.getAttribute("msg");
           if (message!=null) { %>
           <p style="color: red; text-align: center;font-weight: bold;"><%=message%></p>
        <% } %>
    </div>
</div>
</body>
</html>