<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<head>
    <title>注册</title>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reg.css">
    <script src="${pageContext.request.contextPath}/js/reg.js"></script>
    <style>
        .site-link {
            padding: 5px 15px;
            position: fixed;
            z-index: 99999;
            background: #fff;
            box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
            right: 30px;
            bottom: 30px;
            border-radius: 10px;
        }

        .site-link img {
            width: 30px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="body">
    <div class="veen">
        <div class="login-btn splits">
            <p>还没有账号?</p>
            <button class="active">注册</button>
        </div>
        <div class="rgstr-btn splits">
            <p>已有账号?</p>
            <button>登录</button>
        </div>
        <div class="wrapper" style="height: 525px;">
            <form id="login" tabindex="502" action="" method="post" name="form1" style="padding-top: 40px;">
                <h3>注册</h3>
                <div class="name">
                    <input type="text" name="username" value="${userName}" required="required">
                    <label>昵称</label>
                </div>
                <div class="mail">
                    <input type="mail" name="email" value="${mail}" required="required">
                    <label>邮箱</label>
                </div>
                <div class="passwd">
                    <input type="password" name="password" value="${password}" required="required">
                    <label>密码</label>
                </div>
                <span style="font-weight: lighter;font-size: 12px">*最少6位,包括至少1个大写字母,1个小写字母,1个数字,1个特殊字符</span><br><br>
                <div class="passwd">
                    <input type="password" name="password2" value="${password2}" required="required">
                    <label>确认密码</label>
                </div>
                <div class="code">
                    <input type="text" name="code" value="${code}">
                    <label>邮箱验证码</label>
                </div>
                <div class="submit">
                    <button class="dark" onclick="form1.action='/sendmail';form1.submit();">&emsp;获取验证码&emsp;
                    </button>

                    <button class="dark" onclick="form1.action='/login';form1.submit();">&emsp;&ensp;&emsp;&nbsp;注册&nbsp;&emsp;&emsp;&emsp;</button>
                </div>
                <p style="color: red;font-weight: bold;">${msg}</p>
            </form>
            <form id="register" tabindex="500" action="/signup" method="post" style="padding-bottom: 300px;">
                <h3>登录</h3>
                <div class="name">
                    <input type="text" name="userNameOrMail" required="required">
                    <label>用户名或邮箱</label>
                </div>
                <div class="passwd">
                    <input type="password" name="password" required="required">
                    <label>密码</label>
                </div>
                &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                <a href="/forgetpwd">忘记密码?</a>
                <br>
                <div class="submit">
                    <button class="dark">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;登录&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</button>
                </div>
                <p style="color: red;font-weight: bold;">${msg2}</p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
