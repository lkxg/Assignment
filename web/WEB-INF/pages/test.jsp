<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://cdn.tiny.cloud/1/nfr5439i038n4jrl7s55m1td7m1kse839qb97ne0yzcxbj0l/tinymce/6/plugins.min.js" referrerpolicy="origin"></script>
    <script>
        tinymce.init({
            selector: '#textarea',
        });
    </script>
    <title>test</title>
</head>
<body>
<div class="form-group">
<textarea id="textarea">
    Hello,TinyMCE!
  </textarea>
</div>
</body>
</html>