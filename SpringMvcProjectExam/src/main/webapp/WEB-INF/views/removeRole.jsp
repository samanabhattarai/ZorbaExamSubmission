<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Remove role</title>
</head>
<body>
<h4>Roles for user ${user.name}</h4>

<c:if test="${message != null}">
    ${message}
</c:if>

<form action="/SpringWebMvcExam_war/users/roles/delete/${user.userId}" name="removeRole" method="post">
    <c:if test="${empty user.roles}">
        No roles for this user!!!
    </c:if>
    <c:forEach var="role" items="${user.roles}" varStatus="loop">
        <input type="checkbox" name="roleNames"  id="roleNames-${loop.index}" value="${role.roleName}"/>
        <label for="roleNames-${loop.index}">${role.roleName}</label>
    </c:forEach>

    <br/>
    <br/>
    <input type="submit" value="Remove Roles"/>
</form>

<br>
<ul>
    <li><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/register">Register User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/">All Users</a></li>
</ul>

</body>
</html>
