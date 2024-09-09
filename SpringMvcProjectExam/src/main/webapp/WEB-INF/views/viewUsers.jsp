<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.springmvc.model.UserModel"  %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Users View</title>
</head>
<body>

<c:if test="${message != null}">
    <h4>${message}</h4>
</c:if>

<table border="1">
    <tr>
        <th>User Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Username</th>
        <th>Password</th>
        <th>Roles</th>
    </tr>
    <c:forEach items="${users}" var="userModel">
        <tr>
            <td><a href="/SpringWebMvcExam_war/users/roles/${userModel.userId}">${userModel.userId}</a></td>
            <td><a href="/SpringWebMvcExam_war/users/user/${userModel.userId}">${userModel.name}</a></td>
            <td>${userModel.email}</td>
            <td>${userModel.mobile}</td>
            <td>${userModel.userName}</td>
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
        </tr>
    </c:forEach>
</table>
<br>
<ul>
    <li><a href="/SpringWebMvcExam_war/users/register">Register User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/">Show Users</a></li>
</ul>
</body>
</html>
