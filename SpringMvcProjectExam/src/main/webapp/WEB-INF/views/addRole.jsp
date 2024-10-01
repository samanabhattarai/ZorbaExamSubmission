<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Role</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>

<form action="/SpringWebMvcExam_war/users/roles/${user.userId}" method="POST" name="addRole" class="form-signin w-100 m-auto">
    <h4 class="h3 mb-3 fw-normal">Roles for user ${user.name}!</h4>

    <c:if test="${message != null}">
        ${message}
    </c:if>

    <div>
        <label for="roleNames">Roles</label>
        <select name="roleNames" class="form-control" id="roleNames" multiple>
            <option value="ADMIN">ADMIN</option>
            <option value="CUSTOMER">CUSTOMER</option>
            <option value="VENDOR">VENDOR</option>
        </select>
    </div>
    <div>
        <input type="submit" class="form-control" name="submit" value="Submit"/>
    </div>
</form>

<br>
<ul>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">All Users</a></p></li>
</ul>

<footer>

    <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
</footer>
</body>
</html>
