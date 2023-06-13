<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新闻发布</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://cdn.tiny.cloud/1/nfr5439i038n4jrl7s55m1td7m1kse839qb97ne0yzcxbj0l/tinymce/6/plugins.min.js" referrerpolicy="origin"></script>
    <script>
        tinymce.init({
            selector: '#textarea',
            language: 'zh-Hans',
            branding: false,
            width: '100%',
            height: 800,
            plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed linkchecker a11ychecker tinymcespellchecker permanentpen powerpaste advtable advcode editimage tinycomments tableofcontents footnotes mergetags autocorrect typography inlinecss',
            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
            tinycomments_mode: 'embedded',
            tinycomments_author: 'Author name',
            mergetags_list: [
                { value: 'First.Name', title: 'First Name' },
                { value: 'Email', title: 'Email' },
            ]
        });
    </script>
    <style>
        /* 通用样式 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        /* 自定义样式 */
        form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 60%;
            margin-bottom: 10px;
            padding: 5px;
            border:1px solid #9bc0dd;
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
        .shortselect{
            background:#fafdfe;
            height:28px;
            width:180px;
            line-height:28px;
            border:1px solid #9bc0dd;
            -moz-border-radius:2px;
            -webkit-border-radius:2px;
            border-radius:2px;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="big">
<div class="container" style=" padding: 20px 100px;">
    <h1>发布新闻</h1>
    <form method="post" enctype="multipart/form-data" action="/releasenews">
        <div class="form-group">
            <label for="title">标题</label><br>
            <input type="text" id="title" name="title" required>
        </div>
        <br>
        <div>
            <label for="file">封面</label>
          <br>
        <input type="file" name="file" id="file"  onchange="previewImage(event)" style="position: absolute; opacity: 0;width: 150px;height: 100px;">
            <img src="https://www.helloimg.com/images/2023/06/01/om2IC5.png" id="preview"  width="150" height="100" style="border-radius: 5px;">
        <script>
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function() {
                    var output = document.getElementById('preview');
                    output.src = reader.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            }
        </script>
        </div>
        <br>
        <div class="form-group">
            <label for="summary">摘要</label>
            <br>
            <input type="text" id="summary" name="abstracts" required>
        </div>
        <br>
        <div class="select">
            <label for="select">类型</label>
            <br>
            <select name="newsType" id="select" class="shortselect">
                <option value="">---请选择---</option>
                <option value="yule">娱乐</option>
                <option value="tiyu">体育</option>
                <option value="keji">科技</option>
                <option value="dianying">电影</option>
                <option value="youxi">游戏</option>
                <option value="ruanjian">软件</option>
                <option value="qita">其他</option>
            </select>
        </div>
        <br>
        <br>
     <div class="select">
        <label for="mySelect">来源</label>
        <br>
        <select name="origin" id="mySelect" class="shortselect" onchange="showInput()">
            <option value="">---请选择---</option>
            <option value="original">原创</option>
            <option value="reprint">转载</option>
        </select>
    </div>
        <br>
        <div id="inputBox" style="display:none;">
            <input type="text" id="myInput" name="reprint" placeholder="原文链接">
        </div>
        <script>
            function showInput() {
                var selectBox = document.getElementById("mySelect");
                var inputBox = document.getElementById("inputBox");
                var selectedValue = selectBox.options[selectBox.selectedIndex].value;
                if (selectedValue == "reprint") {
                    inputBox.style.display = "block";
                } else {
                    inputBox.style.display = "none";
                }
            }
        </script>
        <br>
        <div class="form-group">
            <label for="textarea">内容</label>
            <br>
            <textarea id="textarea" name="content" >
            </textarea>
        </div>
        <br>
        <button type="submit">发布</button>
        &emsp;
        <button type="reset" style="background-color: red;">取消</button>
        <br>
        <br>
        <% if (request.getAttribute("msg") != null) { %>
        <p style="color: red;text-align: center;font-weight: bold;">
            <%= request.getAttribute("msg") %>
        </p>
        <% } %>
    </form>
</div>
</div>
</body>
</html>
