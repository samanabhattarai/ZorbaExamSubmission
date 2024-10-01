<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>

<h2 class="h3 mb-3 fw-normal">Welcome to customer dashboard: ${user.getName()} !</h2>

<c:if test="${message != null}">
    ${message}
</c:if>
<br />

<form action="/SpringWebMvcExam_war/customer/${user.getUserId()}/addToCart" method="POST" name="addToCart" class="form-signin w-100 m-auto">
    <label for="categoryId">Select Category:</label>
    <select name="categoryId" class="form-control" id="categoryId">
       ${categoryList}
    </select>

    <c:if test="${inventoryList != null}">
    <table border="1" class="table table-primary table-striped-columns">
            <tr>
                <th>Select Product</th>
                <th>Inventory Name</th>
                <th>Inventory Quantity</th>
                <th>Inventory Price</th>
                <th>Inventory Image</th>
                <th>Inventory Description</th>
            </tr>
    </c:if>
    <c:forEach var="inventory" items="${inventoryList}" varStatus="loop">
        <tr>
            <td><input type="radio" name="inventoryId" class="form-control" value="${inventory.inventoryId}" id="inventoryId-${loop.index}" /></td>
            <td>${inventory.name}</td>
            <td>${inventory.quantity}</td>
            <td>${inventory.price}</td>
            <td><img src="${inventory.image}" alt="Product Image" /></td>
            <td>${inventory.description}</td>
        </tr>
    </c:forEach>


    <c:if test="${inventoryList != null}">
        </table>
    </c:if>


    <c:choose>
        <c:when test="${inventoryList == null}">
            <br/>
            <input type="submit" class="form-control" value="Select Product" />
        </c:when>
        <c:otherwise>
            <input type="submit" class="form-control" value="Add to Cart" />
        </c:otherwise>
    </c:choose>

</form>

<ul>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/register">Register User</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/users/">All Users</a></p></li>
    <li><p class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/uploadInventory">Add-to-Cart</a></p></li>

</ul>

<footer>

    <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
</footer>
</body>
</html>
