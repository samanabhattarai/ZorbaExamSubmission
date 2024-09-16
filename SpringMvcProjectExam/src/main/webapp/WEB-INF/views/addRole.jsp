<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Role</title>
</head>
<body>
<form action="/SpringWebMvcExam_war/users/roles/${user.userId}" method="POST" name="addRole">
    <h4>Roles for user ${user.name}!</h4>

    <c:if test="${message != null}">
        ${message}
    </c:if>

    <div>
        <label for="roleNames">Roles</label>
        <select name="roleNames" id="roleNames" multiple>
            <option value="ADMIN">ADMIN</option>
            <option value="CUSTOMER">CUSTOMER</option>
            <option value="VENDOR">VENDOR</option>
        </select>
    </div>
    <div>
        <input type="submit" name="submit" value="Submit"/>
    </div>
</form>

<br>
<ul>
    <li><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/register">Register User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/">All Users</a></li>
</ul>
</body>
</html>
