<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title></title>
</head>
<body>

<h2>Admin Dashboard</h2>

<h3>Customer Information</h3>
<table border="1">

  <thead>
  <tr>

    <th>User Name</th>
    <th>Password</th>
    <th>RoleName</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.name}</td>
      <td>${customer.userName}</td>
      <td>${customer.password}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h3>Vendor Information</h3>
<table border="1">
  <thead>
  <tr>
    <th>User Name</th>
    <th>Password</th>
    <th>RoleName</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="vendor" items="${vendors}">
    <tr>
      <td>${vendor.name}</td>
      <td>${vendor.userName}</td>
      <td>${vendor.password}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<br>
<a href="viewInventory">View Inventory</a>

</body>
</html>
