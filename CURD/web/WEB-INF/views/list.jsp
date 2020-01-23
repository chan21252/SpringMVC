<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>所有员工信息</title>
    <script type="text/javascript" src="scripts/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                let href = this.href;
                $("#hidden-form").attr("action", href).submit();
                return false;
            })
        });
    </script>
</head>
<body>
<c:if test="${empty employees}">
    没有员工信息
</c:if>
<c:if test="${!empty employees}">
    <table border="1" cellspacing="0" cellpadding="10">
        <thead>
            <tr>
                <td>ID</td>
                <td>LastName</td>
                <td>Email</td>
                <td>Gender</td>
                <td>Department</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender}</td>
                    <td>${emp.department.departmentName}</td>
                    <td><a href="/emp/${emp.id}">edit</a></td>
                    <td><a href="/emp/${emp.id}" class="delete">delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<a href="/emp">添加员工</a>

<form id="hidden-form" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
</body>
</html>
