<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Inventory</title>
</head>
<body>
    <h4>Add Inventory</h4>
    <c:if test="${message != null}">
        ${message}
    </c:if>
    <br />

    <form action="/SpringWebMvcExam_war/inventory" method="post">
        <label for ="category">Inventory Category</label>
        <select name="category" id="category">
            <option value="Grocery">Grocery</option>
            <option value="Cosmetics">Cosmetics</option>
            <option value="Dairy">Dairy</option>
        </select><br><br>

        <label for="name">Inventory Name</label>
        <input type="text" name="name" id="name" placeholder="Rice, Flour, Soap , Milk"><br><br>
        <label for="quantity">Inventory Quantity</label>
        <input type="text" name="quantity" id="quantity"><br><br>
        <label for="price">Inventory Price</label>
        <input type ="text" name="price" id="price"><br><br>
        <label for="image">Inventory Image</label>
        <input type="text" name="image" id="image"><br><br>
        <label for="description">Inventory Description</label>
        <textarea name="description" id="description"  rows="4" cols="50"></textarea>
        <br><br>
        <input type="submit" value="Add Inventory">
    </form>

    <ul>
        <li><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></li>
        <li><a href="/SpringWebMvcExam_war/users/register">Register User</a></li>
        <li><a href="/SpringWebMvcExam_war/users/">All Users</a></li>
    </ul>

</body>
</html>
