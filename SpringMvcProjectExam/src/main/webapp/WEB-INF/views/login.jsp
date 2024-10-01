<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Customer Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>

<body class="text-center">
<div class="container-sm">
    <form action="/SpringWebMvcExam_war/login" method="post" class="form-signin w-100 m-auto">
        <h1 class="h3 mb-3 fw-normal">Please Log In</h1>

        <c:if test="${param.error != null}">
            <div style="color:red;">
                Invalid username and password.
            </div>
            <br />
        </c:if>

        <c:if test="${param.logout != null}">
            <div style="color:green;">
                You have been logged out.
            </div>
            <br />
        </c:if>

        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter User Name">
            <label for="username">Username</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
            <label for="password">Password</label>
        </div>

        <div class="form-floating my-3">
            <select name="roleName" id="roleName" class="form-select">
                <option value="ADMIN">Admin</option>
                <option value="VENDOR">Vendor</option>
                <option value="CUSTOMER">Customer</option>
            </select>
            <label for="roleName">Role</label>
        </div>
        <button class="btn btn-outline-primary w-100 py-2" type="submit">Log in</button>
        <p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p>
    </form>
</div>



<footer>

    <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
</footer>

</body>
</html>
