<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Vendor Login</title>
</head>
<body>
    <h4>Vendor Login</h4>
    <br />
    <c:if test="${message != null}">
        ${message}
    </c:if>

    <br />
    <form action="/SpringWebMvcExam_war/vendor/login" method="post">
        <label for ="userName">User Name:</label>
        <input type="text" name="userName" id="userName"><br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" id ="password"><br><br>
        <label for="role">Role:</label>
        <input type="text" name="role" id="role" value="Vendor" readonly><br><br>
        <input  type="hidden" name="roleName" id="roleName" value="VENDOR">
        <input type="submit" value="Login">
    </form>
</body>
</html>
