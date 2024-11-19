<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加新学生</title>
        <base href="${pageContext.request.contextPath}/">
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/style.css"/>
    </head>
    <body>
        <div class="container text-center">
            <br>
            <h2 class="text-success bg-success padding10">添加新学生</h2>
            <hr>
            <br>
            <!-- 提交表单，发送一个添加新学生的请求 -->
            <form action="students/add_student" class="form-inline" method="post">
                <span>学号：</span><input type="text" name="srollno" value="2022209999" class="form-control" placeholder="在此输入学号">
                <br><br>
                <span>姓名：</span><input type="text" name="sname" value="张三" class="form-control" placeholder="在此输入姓名">
                <br><br>
                <span>密码：</span><input type="text" name="spassword" value="666666" class="form-control" placeholder="在此输入密码">
                <br><br>
                <span>性别：</span>
                <select name="sgender" class="form-control fixedWidth">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
                <br><br>
                <span>班级：</span>
                <select name="sbatch" class="form-control fixedWidth">
                    <option >请选择班级</option>
                    <option value="22软件外包01班">22软件外包01班</option>
                    <option value="22软件外包02班">22软件外包02班</option>
                </select>  
                <br><br>
                <input type="submit" value="添 加" class="btn btn-sm btn-primary">
            </form>
            <br>
            <a href="index">返回首页</a>
        </div>
    </body>
</html>
