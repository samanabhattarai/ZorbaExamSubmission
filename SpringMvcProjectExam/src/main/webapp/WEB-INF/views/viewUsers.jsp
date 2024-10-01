<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.springmvc.model.UserModel"  %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>User Views</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>
<h1 class="h3 mb-3 fw-normal">Users Details</h1>

<c:if test="${message != null}">
    <h4>${message}</h4>
</c:if>

<table border="1" class="table table-primary table-striped-columns">
    <tr>
        <th>User Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Username</th>
        <th>Password</th>
        <th>Roles</th>
        <th>Download</th>
    </tr>
    <c:forEach items="${users}" var="userModel">
        <tr>
            <td><a href="/SpringWebMvcExam_war/users/roles/${userModel.userId}">${userModel.userId}</a></td>
            <td><a href="/SpringWebMvcExam_war/users/user/${userModel.userId}">${userModel.name}</a></td>
            <td>${userModel.email}</td>
            <td>${userModel.mobile}</td>
            <td>${userModel.username}</td>
            <td>${userModel.password}</td>
            <td>
                <c:if test="${ not empty userModel.roles}">
                    <a href="/SpringWebMvcExam_war/users/roles/delete/${userModel.userId}">
                        <c:forEach var="role" items="${userModel.roles}" varStatus="loop">
                            ${role.roleName}
                            <c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                    </a>
                </c:if>
            </td>
            <td><a href="/SpringWebMvcExam_war/users/download">Download</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<ul>
    <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p></li>
    <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">Show Users</a></p></li>
    <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/logout">Logout</a></p></li>
</ul>

    <footer>

    <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
</footer>

</body>
</html>
