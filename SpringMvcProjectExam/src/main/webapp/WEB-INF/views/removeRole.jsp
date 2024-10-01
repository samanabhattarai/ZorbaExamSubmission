<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Remove role</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>
<h4 class="h3 mb-3 fw-normal">Roles for user ${user.name}</h4>

<c:if test="${message != null}">
    ${message}
</c:if>

<form action="/SpringWebMvcExam_war/users/roles/delete/${user.userId}" name="removeRole" method="post" class="form-signin w-100 m-auto">
    <c:if test="${empty user.roles}">
        No roles for this user!!!
    </c:if>
    <c:forEach var="role" items="${user.roles}" varStatus="loop">
        <input type="checkbox" class="form-control" name="roleNames"  id="roleNames-${loop.index}" value="${role.roleName}"/>
        <label for="roleNames-${loop.index}">${role.roleName}</label>
    </c:forEach>

    <br/>
    <br/>
    <input type="submit" class="form-control" value="Remove Roles"/>
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
