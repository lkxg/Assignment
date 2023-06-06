<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <meta charset="utf-8">
    <style>
        /* 自定义样式 */
        .container {
            margin-top: 50px;
        }
        table th, table td {
            text-align: center;
        }
        .btn {
            margin: 5px;
            padding: 5px 10px;
            font-size: 14px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #337ab7;
            border-color: #2e6da4;
            color: #fff;
        }
        .btn-danger {
            background-color: #d9534f;
            border-color: #d43f3a;
            color: #fff;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container">
    <h1>用户管理</h1>
    <div class="row">
        <div class="col-sm-12">
            <!-- 用户列表 -->
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>张三</td>
                    <td>zhangsan@example.com</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>李四</td>
                    <td>lisi@example.com</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>王五</td>
                    <td>wangwu@example.com</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- 添加用户表单 -->
            <form>
                <div class="form-group">
                    <label for="username">用户名：</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">邮箱：</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <button type="submit" class="btn btn-primary">添加用户</button>
            </form>
        </div>
    </div>
</div>
<script>
    // 添加用户
    $('form').submit(function(event) {
        event.preventDefault();
        var username = $('#username').val();
        var email = $('#email').val();
        var html = '<tr><td></td><td>' + username + '</td><td>' + email + '</td><td><button class="btn btn-primary">编辑</button><button class="btn btn-danger">删除</button></td></tr>';
        $('table tbody').append(html);
        // 为新添加的用户设置编号
        $('table tbody tr').each(function(index) {
            $(this).find('td:first').text(index + 1);
        });
        $('#username').val('');
        $('#email').val('');
    });
    // 编辑用户
    $('table').on('click', '.btn-primary', function() {
        var row = $(this).parents('tr');
        var username = row.find('td:eq(1)').text();
        var email = row.find('td:eq(2)').text();
        $('#username').val(username);
        $('#email').val(email);
        // 删掉原行
        row.remove();
    });
    // 删除用户
    $('table').on('click', '.btn-danger', function() {
        var row = $(this).parents('tr');
        row.remove();
        // 重新设置编号
        $('table tbody tr').each(function(index) {
            $(this).find('td:first').text(index + 1);
        });
    });
</script>
</body>
</html>