<%--
  Created by IntelliJ IDEA.
  User: sujan
  Date: 9/1/2024
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users View</title>
</head>
<body>

<body>
<c:set var="userObj" value="${userObj}"/>
<table>
    <tr>
        <th>User Id</th>
        <th>Name</th>
        <th>User Email</th>
        <th>User Mobile</th>
        <th>User Name</th>
        <th>User Password</th>
    </tr>
    <tr>
        <td>${studObj.userId}</td>
        <td>${studObj.Name}</td>
        <td>${studObj.userEmail}</td>
        <td>${studObj.userMobile}</td>
        <td>${studObj.userName}</td>
        <td>${studObj.userPassword}</td>
    </tr>
</table>
</body>

</body>
</html>
