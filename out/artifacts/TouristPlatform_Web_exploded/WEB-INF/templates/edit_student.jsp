<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑学生</title>
        <base href="${pageContext.request.contextPath}/">
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/style.css"/>
    </head>
    <body>
        <div class="container text-center">
            <br>
            <h2 class="text-danger bg-danger padding10">编辑学生信息</h2>
            <hr>
            <br>
             <!-- 提交表单，发送一个修改学生信息的请求 -->
             <form action="students/edit_student" class="form-inline" method="post">
                <span>学号：</span>
                <input type="text" name="srollno" class="form-control" readonly value="${student.srollno}">
                <br><br>
                <span>姓名：</span>
                <input type="text" name="sname" class="form-control" value="${student.sname}">
                <br><br>
                <span>密码：</span>
                <input type="text" name="spassword" class="form-control" value="${student.spassword}">
                <br><br>
                <span>性别：</span>
                <input type="text" name="sgender" class="form-control" value="${student.sgender}" readonly>   
                <br><br>
                <span>班级：</span>
                <input type="text" name="sbatch" class="form-control" value="${student.sbatch}" readonly>
                <br><br>
                <input type="submit" value="修 改" class="btn btn-sm btn-primary">
            </form>
            <br>
            <a href="index">返回首页</a>
        </div>
    </body>
</html>
