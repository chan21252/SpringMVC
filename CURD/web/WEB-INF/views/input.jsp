<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工信息</title>
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
        birthday:<form:input path="birthday"/>
        <br/>
        salary:<form:input path="salary"/>
        <br/>
        <input type="submit" value="提交"/>
    </form:form>

    <!--
        测试自定义类型转换器
        输入格式字符串提交，转为Employee对象
        lastName-email-gender-departmentId
    -->
    <form action="${pageContext.request.contextPath}/testConversionService" method="post">
        <label>
            员工:<input type="text" name="employee"/>
        </label>
        <br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
