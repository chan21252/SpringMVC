<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>所有员工信息</title>
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
                    <td><a href="/edit">edit</a></td>
                    <td><a href="/delete">delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="/emp">添加员工</a>
</body>
</html>
