<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="me.kaixuan.entity.News" %>
<%@ page import="java.util.List" %>
<%@ page import="me.kaixuan.entity.Comment" %>
<%@ page import="me.kaixuan.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新闻</title>
    <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://cdn.tiny.cloud/1/nfr5439i038n4jrl7s55m1td7m1kse839qb97ne0yzcxbj0l/tinymce/6/plugins.min.js" referrerpolicy="origin"></script>
    <script>
        tinymce.init({
            selector: '#textarea',
            language: 'zh-Hans',
            branding: false,
            height: 200,
            plugins: 'advlist autolink lists link image charmap print preview hr anchor pagebreak ' +
                'searchreplace wordcount visualblocks visualchars code fullscreen ' +
                'insertdatetime media nonbreaking save table contextmenu directionality ' +
                'emoticons template paste textcolor colorpicker textpattern imagetools',
            toolbar: 'undo redo styleselect bold italic link image forecolor backcolor emoticons code',
        });
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
        }
        header {
            background-color: #eeeeee;
            text-align: center;
            color: #000000;
            padding: 20px 50px;
        }
        h1 {
            margin: 0;
        }
        .meta {
            margin-top: 10px;
            font-size: 14px;
            color: #29c5eb;
        }
        .author, .date {
            font-weight: bolder;
            font-size: medium;
            margin-right: 20px;
        }
        main {
            background-color: white;
            padding: 10px 30px;
        }
        article {
            line-height: 1.5;
        }
        form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            margin-bottom: 10px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button {
            background-color: #337ab7;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
        .comment-wrapper {
            margin-top: 20px;
        }
        .comment-box {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
        }
        .comment-box p {
            margin: 0;
            padding: 0;
            font-weight: bold;
        }
        .comment-box small {
            color: #aaa;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<header>
    <h1>${news.title}</h1>
    <br>
    <div class="meta">
        <span class="author" style="margin-left: 450px">作者：${news.user.username}</span>
        <% News news = (News) request.getAttribute("showNews");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String pTime = sdf.format(news.getPublishTime());
        %>
        <span class="date">发布日期：<%=pTime%></span>
    </div>
</header>
<main>
    <article>
      ${news.content}
    </article>
</main>
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
<div class="container" style="margin-left: 30px;margin-right: 200px;margin-top: 10px;margin-bottom: 10px;">
    <form method="post" enctype="multipart/form-data"  action="/addcomment" >
        <div class="form-group">
            <label>评论</label>
            <br>
            <textarea id="textarea" name="content">
            </textarea>
        </div>
        <br>
        <button type="submit">提交评论</button>
    </form>
    <form action="" name="form1">
    <div class="comment-wrapper">
        <% List<Comment> comments = (List<Comment>) request.getAttribute("comments");
        if (comments != null){
               for (Comment comment : comments) {
                   String commentTime = sdf.format(comment.getCommentTime());
        %>
        <div class="comment-box">
            <p><%=comment.getUser().getUsername()%>&emsp;<small><%=commentTime%></small></p>
            <br>
            <%=comment.getContent()%>
        </div>
        <% User user = (User) request.getAttribute("user");
            if(user.getUserType()==3) {%>
        <br>
        <button type="submit" style="background-color: #dc5d5d;padding: 10px 50px" onclick="form1.method='post';form1.action='/deletecomment?commentId=<%=comment.getId()%>';form1.submit();">删除</button>
        <% }%>
        <% }
         }%>
    </div>
    </form>
</div>
</body>
</html>