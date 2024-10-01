<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>View Inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>
<h1 class="h3 mb-3 fw-normal">Inventory Details</h1>

<c:if test="${message != null}">
    <h4>${message}</h4>
</c:if>

<table border="1" class="table table-primary table-striped-columns">
    <tr>
        <th>Inventory Id</th>
        <th>Category</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Image</th>
        <th>Description</th>
    </tr>

    <c:forEach items="${inventoryList}" var="inventoryModel">
    <tr>
        <td><a href="/SpringWebMvcExam_war/inventories/roles/${inventoryModel.inventoryId}">${inventoryModel.inventoryId}</a></td>
        <td><a href="/SpringWebMvcExam_war/inventories/inventory/${inventoryModel.inventoryId}">${inventoryModel.category}</a></td>
        <td>${inventoryModel.name}</td>
        <td>${inventoryModel.quantity}</td>
        <td>${inventoryModel.price}</td>
        <td>${inventoryModel.image}</td>
        <td>${inventoryModel.description}</td>
        <td><a href="/SpringWebMvcExam_war/inventories/delete/${inventoryModel.inventoryId}">Delete</a>
        </td>
    </tr>
    </c:forEach>

</table>
<br>
    <ul>
        <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p></li>
        <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">All Users</a></p></li>
        <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/uploadInventory">Upload Inventory</a></p></li>
        <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/">Home</a></p></li>
        <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/logout">Logout</a></p></li>
    </ul>

    <footer>

        <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
    </footer>

</body>
</html>
