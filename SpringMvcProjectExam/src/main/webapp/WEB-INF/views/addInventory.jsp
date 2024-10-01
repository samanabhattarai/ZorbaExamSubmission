<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>
    <h4 class="h3 mb-3 fw-normal">Add Inventory</h4>
    <c:if test="${message != null}">
        ${message}
    </c:if>
    <br />

    <form action="/SpringWebMvcExam_war/inventory" method="post" class="form-signin w-100 m-auto">
        <label for ="category">Inventory Category</label>
        <select name="category" class="form-control"  id="category">
            <option value="Grocery">Grocery</option>
            <option value="Cosmetics">Cosmetics</option>
            <option value="Dairy">Dairy</option>
        </select><br><br>

        <label for="name">Inventory Name</label>
        <input type="text" class="form-control" name="name" id="name" placeholder="Rice, Flour, Soap , Milk"><br><br>
        <label for="quantity">Inventory Quantity</label>
        <input type="text" class="form-control" name="quantity" id="quantity"><br><br>
        <label for="price">Inventory Price</label>
        <input type ="text" class="form-control" name="price" id="price"><br><br>
        <label for="image">Inventory Image</label>
        <input type="text" class="form-control" name="image" id="image"><br><br>
        <label for="description">Inventory Description</label>
        <textarea name="description" class="form-control" id="description"  rows="4" cols="50"></textarea>
        <br><br>
        <input type="submit" class="form-control" value="Add Inventory">
    </form>

    <ul>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></p></li>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p></li>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">All Users</a></p></li>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/uploadInventory">Upload Inventory</a></p></li>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/inventory">View Inventory</a></p></li>
    </ul>


    <footer>

        <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
    </footer>
</body>
</html>
