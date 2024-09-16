<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Login</title>
</head>
<body>

<h4>Admin Login</h4>
<br />
<c:if test="${message != null}">
  ${message}
</c:if>

<br />
<form action="/SpringWebMvcExam_war/admin/login" method="post">
  <label for ="userName">User Name:</label>
  <input type="text" name="userName" id="userName"><br><br>
  <label for="password">Password:</label>
  <input type="password" name="password" id ="password"><br><br>

  <label for="role">Role:</label>
  <input type="text" name="role" id="role" value="Admin" readonly><br><br>

  <input  type="hidden" name="roleName" id="roleName" value="ADMIN">

  <input type="submit" value="Login">
</form>

</body>
</html>
