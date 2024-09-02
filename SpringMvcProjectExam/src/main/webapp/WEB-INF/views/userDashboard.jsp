<%--
  Created by IntelliJ IDEA.
  User: sujan
  Date: 9/1/2024
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>

<body>
<table>
    <tr>
        <th>Student Id</th>
        <th>Student Name</th>
        <th>Student Address</th>
    </tr>
    <c:forEach var="userObj" items="${allUserInfo}">
        <tr>
            <td>
                <a href="http://localhost:8080/SpringMVCApplication/student/getStudentById/${userObj.userId}">
                    <c:out value="${userObj.userId}"/>
                </a>
            </td>
            <td><c:out value="${userObj.Name}"/></td>
            <td><c:out value="${userObj.userEmail}"/></td>
            <td><c:out value="${userObj.userMobile}"/></td>
            <td><c:out value="${userObj.userName}"/></td>
            <td><c:out value="${userObj.userPassword}"/></td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
