<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工信息管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn-json").click(function () {
                $.ajax({
                    url: "/testJson",
                    method: "GET",
                    success: function (res) {
                        console.log(res);
                        let employeeDiv = $("#employees");
                        employeeDiv.html('');
                        for (let i = 0; i < res.length; i++) {
                            employeeDiv.append('<div>' + res[i].lastName + '</div>')
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<a href="/emps">显示所有员工信息</a>
<br/>

<p>json数据测试</p>
<button id="btn-json">显示所有员工名字</button>
<div id="employees"></div>

<p>@requestBody注解测试</p>
<div>
    <form action="${pageContext.request.contextPath}/testHttpMessageConverter" enctype="multipart/form-data"
          method="post">
        文件：<input type="file" name="file"/><br/>
        <label>
            描述：
            <input type="text" name="desc"/>
        </label>
        <br/>
        <input type="submit" value="提交">
    </form>
</div>

<p>ResponseEntity测试</p>
<div><a href="${pageContext.request.contextPath}/testResponseEntity">下载图片</a></div>

<p>国际化</p>
<div><a href="/i18n">i18n</a></div>

<p>测试文件上传</p>
<div>
    <form action="${pageContext.request.contextPath}/testFileUpload" enctype="multipart/form-data" method="post">
        文件：<input type="file" name="file"/><br/>
        <label>
            描述：
            <input type="text" name="desc"/>
        </label>
        <br/>
        <input type="submit" value="提交">
    </form>
</div>

<p>测试异常处理</p>
<div><a href="${pageContext.request.contextPath}/testExceptionHandler?i=10">测试ExceptionHandler</a></div>
<div><a href="${pageContext.request.contextPath}/testResponseStatusException?i=10">测试ResponseStatusExceptionSolver</a>
</div>
</body>
</html>
