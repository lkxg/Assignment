<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>游戏</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            padding: 0;
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            font-size: 16px;
            line-height: 1.5;
            margin: 0;
        }
        .container {
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            width: 90%;
            max-width: 1200px;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 32px;
            font-weight: bold;
            margin: 0 0 20px;
            text-align: center;
        }
        .row::after {
            content: "";
            display: table;
            clear: both;
        }
        .col-4 {
            float: left;
            width: 33.33%;
            padding: 10px;
        }
        .thumbnail {
            position: relative;
            overflow: hidden;
            border: none;
            border-radius: 0;
            box-shadow: none;
            margin-bottom: 20px;
        }
        .thumbnail img {
            width: 100%;
            height: auto;
            display: block;
        }
        .caption {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.5);
            color: #fff;
            opacity: 0;
            transition: opacity 0.3s;
        }
        .caption:hover {
            opacity: 1;
        }
        .caption h3 {
            font-size: 24px;
            margin: 0 0 10px;
        }
        .caption p {
            margin: 0;
        }
        .article {
            margin-top: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
        }
        .article h2 {
            font-size: 28px;
            font-weight: bold;
            margin-top: 0;
            margin-bottom: 20px;
        }
        .article .meta {
            color: #777;
            margin-bottom: 20px;
        }
        .article img {
            max-width: 100%;
            margin-bottom: 20px;
        }
        .article p {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container">
    <h1>游戏</h1>
    <!-- 新闻列表 -->
    <div class="row">
        <div class="col-4">
            <div class="thumbnail">
                <a href="yule/one.jsp" class="article-link">
                    <img src="https://picsum.photos/500/300?random=1" alt="新闻图片">
                    <div class="caption">
                        <h3>这里是新闻标题</h3>
                        <p class="meta">2019-01-01 10:00:00  来源：新闻网站</p>
                        <p>这里是新闻摘要，可以简单介绍一下新闻的内容。</p>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-4">
            <div class="thumbnail">
                <a href="#" class="article-link">
                    <img src="https://picsum.photos/500/300?random=2" alt="新闻图片">
                    <div class="caption">
                        <h3>这里是新闻标题</h3>
                        <p class="meta">2019-01-01 10:00:00  来源：新闻网站</p>
                        <p>这里是新闻摘要，可以简单介绍一下新闻的内容。</p>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-4">
            <div class="thumbnail">
                <a href="#" class="article-link">
                    <img src="https://picsum.photos/500/300?random=3" alt="新闻图片">
                    <div class="caption">
                        <h3>这里是新闻标题</h3>
                        <p class="meta">2019-01-01 10:00:00  来源：新闻网站</p>
                        <p>这里是新闻摘要，可以简单介绍一下新闻的内容。</p>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>