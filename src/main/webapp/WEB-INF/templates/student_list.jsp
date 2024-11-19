<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${pageContext.request.contextPath}/">
        <title>学生列表</title>
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/style.css"/>
        <script src="static/js/jquery-3.4.1.min.js"></script>
    </head>
    <body>

        <div class="container text-center">
            <br>
            <h1 class="text-warning">学生列表</h1>
            <hr>
            <a href="students/to_add_student" class="btn btn-sm btn-success">+添加新学生</a>
            &nbsp;&nbsp;
            <a href="index" class="btn btn-sm btn-info">&lt;&lt;&nbsp;返回首页</a>
            <br>
            <hr>

            <table class="table table-hover table-striped">
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>性别</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
                <!--在此遍历学生列表，显示一个班级的所有学生的信息-->
                <c:forEach items="${studentList}" var="s">
                    <tr id="tr${s.srollno}">
                        <td>${s.srollno}</td>
                        <td>${s.sname}</td>
                        <td>${s.spassword}</td>
                        <td>${s.sgender}</td>
                        <td>${s.sbatch}</td>
                        <td>
                            <a href="students/to_edit_student/${s.srollno}">编辑</a>&nbsp;
                            <a href="javascript:deleteStudent('${s.srollno}');">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <script>  //定义一个函数,根据学号删除学生
                function deleteStudent(stuId) {
                    $.ajax({
                        url: 'students/delete',
                        type: 'POST',
                        data: {srollno: stuId}, //发送一个请求参数，参数名为stuId，参数值为传入的stuId变量的值
                        success: function () {
                            //如果能执行到success，说明后台删除成功，这里同时将表格中对应的数据行删除
                            $("#tr" + stuId).remove(); // remove是删除当前元素和其内容
                        },
                        error: function (req, status, error) {
                            alert("Ajax请求失败，错误：" + error);
                        }
                    });
                }
            </script>

        </div>
    </body>
</html>
