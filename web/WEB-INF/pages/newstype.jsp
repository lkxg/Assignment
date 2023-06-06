<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新闻分类管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap 样式 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
<div class="container">
    <h1>新闻分类管理</h1>
    <div class="row">
        <div class="col-sm-12">
            <!-- 新闻分类列表 -->
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>分类名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>国内新闻</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>国际新闻</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>科技新闻</td>
                    <td>
                        <button class="btn btn-primary">编辑</button>
                        <button class="btn btn-danger">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <!-- 添加新闻分类表单 -->
            <form>
                <div class="form-group">
                    <label for="category">分类名称：</label>
                    <input type="text" class="form-control" id="category" name="category" required>
                </div>
                <button type="submit" class="btn btn-primary">添加分类</button>
            </form>
        </div>
    </div>
</div>
<!-- 引入 jQuery 和 Bootstrap JavaScript 脚本 -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    // 添加新闻分类
    $('form').submit(function(event) {
        event.preventDefault();
        var category = $('#category').val();
        var html = '<tr><td></td><td>' + category + '</td><td><button class="btn btn-primary">编辑</button><button class="btn btn-danger">删除</button></td></tr>';
        $('table tbody').append(html);
        // 为新添加的分类设置编号
        $('table tbody tr').each(function(index) {
            $(this).find('td:first').text(index + 1);
        });
        $('#category').val('');
    });
    // 编辑新闻分类
    $('table').on('click', '.btn-primary', function() {
        var row = $(this).parents('tr');
        var category = row.find('td:eq(1)').text();
        $('#category').val(category);
        // 删掉原行
        row.remove();
    });
    // 删除新闻分类
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
