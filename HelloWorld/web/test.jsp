<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
    <table>
        <tbody>
            <tr>
                <td>测试请求方法</td>
                <td><a href="${pageContext.request.contextPath}/springmvc/testMethod">Test Get Method</a></td>
                <td><form action="${pageContext.request.contextPath}/springmvc/testMethod" method="post"><input type="submit" value="Test Post Method"></form></td>
            </tr>
            <tr>
                <td>测试请求参数</td>
                <td><a href="${pageContext.request.contextPath}/springmvc/testParams?username=chan&password=123">Test Params 1</a></td>
                <td><a href="${pageContext.request.contextPath}/springmvc/testParams?username=admin&password=123">Test Params 2</a></td>
            </tr>
            <tr>
                <td>测试请求头</td>
                <td><a href="${pageContext.request.contextPath}/springmvc/testHeaders">Test Headers</a></td>
            </tr>
            <tr>
                <td>测试路径占位符</td>
                <td><a href="${pageContext.request.contextPath}/springmvc/testVariable/1">Test PathVariable</a></td>
            </tr>
        </tbody>
    </table>

    <p>测试Rest风格请求</p>
    <a href="${pageContext.request.contextPath}/springmvc/testRest/1" target="_blank">Test Get</a>
    <form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="post">
        <input type="submit" value="Test Post">
    </form>

    <form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="post">
        <input type="hidden" name="_method" value="delete">
        <input type="submit" value="Test Delete">
    </form>

    <form action="${pageContext.request.contextPath}/springmvc/testRest/1" method="post">
        <input type="hidden" name="_method" value="put">
        <input type="submit" value="Test Put">
    </form>

    <a href="${pageContext.request.contextPath}/springmvc/testParam?username=admin&age=11" target="_blank">Test Params</a>

    <form action="${pageContext.request.contextPath}/springmvc/testPojo" method="post">
        <label for="username">姓名：</label><input type="text" name="username" id="username"/><br/>
        <label for="password">密码：</label><input type="password" name="password" id="password"/><br/>
        <label for="province">省份：</label><input type="text" name="address.province" id="province"/><br/>
        <label for="city">城市：</label><input type="text" name="address.city" id="city"/><br/>
        <input type="submit" value="Test Pojo">
    </form>

    <a href="${pageContext.request.contextPath}/springmvc/testModelAndView" target="_blank">Test Model And View</a><br/>
    <a href="${pageContext.request.contextPath}/springmvc/testMap" target="_blank">Test Map</a>
    <a href="${pageContext.request.contextPath}/springmvc/testSessionAttr" target="_blank">Test Session Attributes</a>
</body>
</html>
