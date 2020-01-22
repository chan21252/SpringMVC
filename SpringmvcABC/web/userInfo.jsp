<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/springmvc/updateUser" method="POST">
    <input type="hidden" name="id" value="1"/><br/>
    <label for="username">用户名：</label><input type="text" name="username" id="username"/><br/>
    <label for="password">密码：</label><input type="password" name="password" id="password"/><br/>
    <label for="email">邮箱：</label><input type="text" name="email" id="email"/><br/>
    <label for="age">年龄：</label><input type="text" name="age" id="age"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
