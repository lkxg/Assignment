<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>评论</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入Bootstrap样式 -->
  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <style>
    /* 自定义样式 */
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
      margin-bottom: 10px;
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
<div class="container">
  <h1>评论页面</h1>
  <form>
    <div class="form-group">
      <label for="name">姓名</label>
      <input type="text" id="name" name="name" required>
    </div>
    <div class="form-group">
      <label for="comment">评论内容</label>
      <textarea id="comment" name="comment" rows="5" required></textarea>
    </div>
    <button type="submit">提交评论</button>
  </form>
  <div class="comment-wrapper">
    <div class="comment-box">
      <p>John Doe <small>2021-05-18</small></p>
      <p>这是一条评论。</p>
    </div>
    <div class="comment-box">
      <p>Jane Doe <small>2021-05-19</small></p>
      <p>这是另一条评论。</p>
    </div>
  </div>
</div>
<!-- 引入jQuery和Bootstrap脚本文件 -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
  // 向评论列表中添加新评论
  function addComment(name, comment) {
    var html = '<div class="comment-box"><p>' + name + '<small>' + getDate() + '</small></p><p>' + comment + '</p></div>';
    $('.comment-wrapper').prepend(html);
  }
  // 获取当前日期
  function getDate() {
    var date = new Date();
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
  }
  // 提交评论
  $('form').submit(function(event) {
    event.preventDefault();
    var name = $('#name').val();
    var comment = $('#comment').val();
    addComment(name, comment);
    $('#name').val('');
    $('#comment').val('');
  });
</script>
</body>
</html>