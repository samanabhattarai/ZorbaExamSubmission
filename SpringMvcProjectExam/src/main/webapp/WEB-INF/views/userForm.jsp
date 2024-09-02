<%--
  Created by IntelliJ IDEA.
  User: sujan
  Date: 9/1/2024
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<form action="saveUser" method="post">
    <div>
        <label for="name"> User Name </label>
        <input name="name" type="text" id="name"/>
    </div>
    <div>
        <label for="email"> User Email</label>
        <input name="email" type="text" id="email">
    </div>
    <div>
        <label for="mobile"> User Mobile</label>
        <input name="mobile" type="text" id="mobile">
    </div>

    <div>
        <label for="userName"> User Name </label>
        <input name="userName" type="text" id="userName"/>
    </div>

    <div>
        <label for="password"> User Password </label>
        <input name="password" type="text" id="password"/>
    </div>


    <div>
        <input type="submit" name="submit" value="Submit"/>
    </div>
</form>

</body>
</html>
