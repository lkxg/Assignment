<%@ page import="me.kaixuan.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>个人资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/info.css">
</head>
<body>
<jsp:include page="head.jsp"/>
<main style="margin-top: 0;">
    <section>
        <h2>基本信息</h2>
        <form action="" name="form1">
            <%  User userManage = (User) request.getSession().getAttribute("userManage");
                User user = (User) request.getSession().getAttribute("user");
                boolean isAdmin = false;
                if(userManage!=null){
                    isAdmin = true;
                }
                %>
            <label for="username">用户ID：</label>
            <input type="text" id="username" name="userId" value="<%=isAdmin?userManage.getId():user.getId()%>" disabled>
            <br>
            <%
            if(userManage!=null){
            isAdmin = true;
            %>
            <label for="userType1">用户级别：</label>
            <label for="userType1">
            <input id="userType1" name="userType" type="radio" value="common" <%=userManage.getUserType()==2?"checked":""%>>普通用户</label>
            <label for="userType2">
            <input id="userType2" name="userType" type="radio" value="admin" <%=userManage.getUserType()==3?"checked":""%>>管理员</label>
            <%}else{%>
            <label for="ok">用户级别：</label>
            <input type="text" id="ok" name="userType" value=${user.userType==3?"管理员":"普通用户"} disabled>
            <%}%>
            <br>
            <label for="nickname">用户昵称：</label>
            <input type="text" id="nickname" name="username" value="<%=isAdmin?userManage.getUsername():user.getUsername()%>">
            <br>
            <label for="email">邮箱：</label>
            <input type="email" id="email" name="email" value="<%=isAdmin?userManage.getEmail():user.getEmail()%>">
            <br>
            <h3>选择一个你喜欢的头像：</h3>
            <div class="avatar-list">
                <label>
                    <input type="radio" name="avatar" value="avatar1">
                    <img src="https://www.helloimg.com/images/2023/05/26/oJpm6S.png" alt="头像1" width="100" height="100">
                </label>
                <label>
                    <input type="radio" name="avatar" value="avatar2">
                    <img src="https://www.helloimg.com/images/2023/06/07/osxC5E.gif" alt="头像2" width="100">
                </label>
                <label>
                    <input type="radio" name="avatar" value="avatar3">
                    <img src="https://www.helloimg.com/images/2023/06/07/osqySu.gif" alt="头像3" width="100">
                </label>
                <label>
                    <input type="radio" name="avatar" value="avatar4">
                    <img src="https://www.helloimg.com/images/2023/06/07/osx5pv.gif" alt="头像4" width="100">
                </label>
            </div>
           <h3>或上传一个头像：</h3>
            <input type="file" name="file" onchange="previewImage(event)" style="position: absolute; opacity: 0;width: 100px;height: 100px;">
            <div class="avatar-list">
                <label>
                    <input type="radio" name="avatar" value="avatar" id="avatar">
                    <img src="https://www.helloimg.com/images/2023/06/03/omzD3A.png" id="preview" alt="头像1" width="100" height="100">
                </label>
            </div>
            <script>
                function previewImage(event) {
                    var reader = new FileReader();
                    reader.onload = function() {
                        var output = document.getElementById('preview');
                        var avatar = document.getElementById('avatar');
                        avatar.checked = true;
                        output.src = reader.result;
                    }
                    reader.readAsDataURL(event.target.files[0]);
                }
            </script>
            <br>
            <button type="submit" onclick="form1.method='post'; form1.enctype='multipart/form-data';form1.action='/updateinfo';form1.submit();">保存修改</button>
        </form>
    </section>
    <section>
        <h2>修改密码</h2>
        <form action="" name="form2">
            <label for="old-password">原密码：</label>
            <input type="password" id="old-password" name="old-password" required="required"><br>
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            <a href="/forgetpwd">忘记密码？</a>
            <br>
            <br>
            <label for="new-password">新密码：</label>
            <input type="password" id="new-password" name="new-password" required="required">
            <span style="font-weight: lighter;font-size: 12px">
            *最少6位,包括至少1个大写字母,1个小写字母,1个数字,1个特殊字符
            </span>
            <br>
            <label for="confirm-password">确认新密码：</label>
            <input type="password" id="confirm-password" name="confirm-password" required="required">
            <br>
            <br>
            <button type="submit" onclick="form2.method='post';form2.action='/updatepwd';form2.submit();">修改密码</button>
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            <button type="submit" style="background-color: red" onclick="form2.method='post';form2.action='/cancelout';form2.submit();">注销账号</button>
            <br>
            <br>
            <br>
            <%if(request.getAttribute("msg")!=null){%>
            &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
            <span style="color: red">${msg}</span>
            <%}%>
        </form>
    </section>
</main>
</body>
</html>