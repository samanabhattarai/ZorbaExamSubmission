<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>

<c:if test="${message != null}">
    <h4>${message}</h4>
</c:if>

<form action="/SpringWebMvcExam_war/users" method="POST" name="userModel">
    <div>
        <label for="name">Name </label>
        <input name="name" type="text" id="name"/>
    </div>
    <div>
        <label for="email">Email</label>
        <input name="email" type="text" id="email">
    </div>
    <div>
        <label for="mobile">Mobile</label>
        <input name="mobile" type="text" id="mobile">
    </div>

    <div>
        <label for="userName">Username</label>
        <input name="userName" type="text" id="userName"/>
    </div>

    <div>
        <label for="password">Password </label>
        <input name="password" type="password" id="password"/>
    </div>

    <br>
    <div>
        <input type="submit" name="submit" value="Submit"/>
    </div>
</form>

<br>
<ul>
    <li><a href="/SpringWebMvcExam_war/users/">Show Users</a></li>
    <li><a href="/SpringWebMvcExam_war/">Home</a></li>
</ul>

</body>
</html>
