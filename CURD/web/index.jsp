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
<button id="btn-json">测试json</button>
<div id="employees"></div>
</body>
</html>
