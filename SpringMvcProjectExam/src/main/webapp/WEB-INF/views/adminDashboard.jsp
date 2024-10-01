<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/application.css">
</head>
<body>

<h2 class="h3 mb-3 fw-normal">Admin Dashboard</h2>

<h3 class="h3 mb-3 fw-normal">Customer Information</h3>
<table border="1" class="table table-primary table-striped-columns">

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
      <td>${customer.username}</td>
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
      <td>${vendor.username}</td>
      <td>${vendor.password}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<br>
<ul>
  <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/inventory">View Inventory</a></p></li>
  <li><p  class="mt-5 mb-3"><a href="/SpringWebMvcExam_war/logout">Logout</a></p></li>
</ul>

<footer>

  <script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
</footer>

</body>
</html>
