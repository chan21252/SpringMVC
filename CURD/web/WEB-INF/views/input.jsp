<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
</head>
<body>
    <%
        String[] genders = {"男", "女"};
        request.setAttribute("genders", genders);
    %>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <c:if test="${employee.id == null}">
            LastName:<form:input path="lastName"/>
        </c:if>
        <c:if test="${employee.id != null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="put">
        </c:if>
        <br/>
        Email:<form:input path="email"/>
        <br/>
        Gender:<form:radiobuttons path="gender" items="${genders}"/>
        <br/>
        Department:<form:select path="department.id" items="${departments}" itemLabel="departmentName" itemValue="id"/>
        <br/>
        <input type="submit" value="submit"/>
    </form:form>
</body>
</html>
