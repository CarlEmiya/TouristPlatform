<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Spring+SpringMVC练习</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="${pageContext.request.contextPath}/">
        <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="static/css/style.css"/>
    </head>
    <body>
        <div class="container text-center">

            <h1 class="text-center text-primary padding10">Spring+SpringMVC练习</h1>
            <hr>
            <br>

 			<!-- 提交表单，发送一个根据班级名称查询学生列表的请求 -->	
            <form action="students/findStudents" class="form-inline">
                <select name="batchName" class="form-control">
                    <option >请选择班级</option>
                    <option value="22软件外包01班">22软件外包01班</option>
                    <option value="22软件外包02班">22软件外包02班</option>
                </select>  
                &nbsp;
                <button class="btn btn-sm btn-primary">查询</button>
            </form>
            
            <br>
            <br>
            <br>
            
        </div>

    </body>
</html>