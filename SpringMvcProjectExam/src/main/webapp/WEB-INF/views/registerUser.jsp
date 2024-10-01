<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body class="text-center">
<div class="container-sm">

    <c:if test="${message != null}">
        <h4>${message}</h4>
    </c:if>


    <form action="/SpringWebMvcExam_war/users/register" method="POST" name="userModel" class="form-signin w-100 m-auto">
        <h1 class="h3 mb-3 fw-normal">Please Register</h1>
        <div class="form-floating mb-3">
            <label for="name">Name </label>
            <input name="name" class="form-control" type="text" id="name" placeholder="Enter Name"/>
        </div><br>
        <div class="form-floating mb-3">
            <label for="email">Email</label>
            <input name="email" class="form-control" type="text" id="email" placeholder="Enter Email"/>
        </div><br>
        <div class ="form-floating mb-3">
            <label for="mobile">Mobile</label>
            <input name="mobile" class="form-control" type="text" id="mobile" placeholder="Enter Mobile"/>
        </div><br>

        <div class ="form-floating mb-3">
            <label for="username">Username</label>
            <input name="username" class="form-control" type="text" id="username" placeholder="Enter Username"/>
        </div><br>

        <div class ="form-floating mb-3">
            <label for="password">Password </label>
            <input name="password" class="form-control" type="password" id="password" placeholder="Enter Password"/>
        </div>

    <br>

    <button class="btn btn-outline-primary w-100 py-2" type="submit">Submit</button>
</form>
</div>

<ul>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">Show Users</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/">Home</a></p></li>
</ul>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>

</body>
</html>
