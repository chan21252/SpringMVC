<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
</head>
<body>
    <!--
    <form action="/emp" method="post">
        <label>LastName:<input type="text" name="lastName"/></label>
        <br/>
        <label>Email:<input type="text" name="email"/></label>
        <br/>
        <label>Gender:
            <input type="radio" name="gender" value="男"/>男
            <input type="radio" name="gender" value="女"/>女
        </label>
        <br/>
        <label>Department:
            <select name="department">
                <c:if test="${empty departments}">
                    <option value="">加载部门信息失败</option>
                </c:if>
                <c:if test="${!empty departments}">
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.departmentName}">${dept.departmentName}</option>
                    </c:forEach>
                </c:if>
            </select>
        </label>
        <br/>
        <input type="submit" value="submit"/>
    </form>
    -->
    <%
        String[] genders = {"男", "女"};
        request.setAttribute("genders", genders);
    %>
    <form:form action="/emp" method="post" modelAttribute="employee">
        LastName:<form:input path="lastName"/>
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
