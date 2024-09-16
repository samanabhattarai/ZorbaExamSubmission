<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>

<h2>Welcome to customer dashboard: ${user.getName()} !</h2>

<c:if test="${message != null}">
    ${message}
</c:if>
<br />

<form action="/SpringWebMvcExam_war/customer/${user.getUserId()}/addToCart" method="POST" name="addToCart">
    <label for="categoryId">Select Category:</label>
    <select name="categoryId" id="categoryId">
       ${categoryList}
    </select>

    <c:if test="${inventoryList != null}">
        <table>
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
            <td><input type="radio" name="inventoryId" value="${inventory.inventoryId}" id="inventoryId-${loop.index}" /></td>
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
            <input type="submit" value="Select Product" />
        </c:when>
        <c:otherwise>
            <input type="submit" value="Add to Cart" />
        </c:otherwise>
    </c:choose>

</form>

<ul>
    <li><a href="/SpringWebMvcExam_war/users/user/${user.userId}">User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/register">Register User</a></li>
    <li><a href="/SpringWebMvcExam_war/users/">All Users</a></li>
    <li><a href="/SpringWebMvcExam_war/uploadInventory">Add-to-Cart</a></li>
</ul>

</body>
</html>
