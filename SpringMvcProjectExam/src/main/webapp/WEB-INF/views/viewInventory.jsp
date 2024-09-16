<%--
  Created by IntelliJ IDEA.
  User: sujan
  Date: 9/15/2024
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Inventory</title>
</head>
<body>


<c:if test="${message != null}">
    <h4>${message}</h4>
</c:if>

<table border="1">
    <tr>
        <th>Inventory Id</th>
        <th>Category</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Image</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${inventories}" var="inventoryModel">
    <tr>
        <td><a href="/SpringWebMvcExam_war/inventories/roles/${inventoryModel.inventoryId}">${inventoryModel.inventoryId}</a></td>
        <td><a href="/SpringWebMvcExam_war/inventories/inventory/${inventoryModel.inventoryId}">${inventoryModel.category}</a></td>
        <td>${inventoryModel.name}</td>
        <td>${inventoryModel.quantity}</td>
        <td>${inventoryModel.price}</td>
        <td>${inventoryModel.image}</td>
        <td>${inventoryModel.description}</td>
        <td>
            <c:if test="${ not empty inventoryModel.roles}">
                <a href="/SpringWebMvcExam_war/inventories/roles/delete/${inventoryModel.inventoryId}">
                    <c:forEach var="role" items="${inventoryModel.roles}" varStatus="loop">
                        ${role.roleName}
                        <c:if test="${!loop.last}">,</c:if>
                    </c:forEach>
                </a>
            </c:if>
        </td>
    </tr>
    </c:forEach>

</body>
</html>
