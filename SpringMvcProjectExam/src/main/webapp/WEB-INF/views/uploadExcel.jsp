<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Excel file</title>
</head>
<body>

<c:if test="${message != null}">
    ${message}
</c:if>

<form method="post" enctype="multipart/form-data"
      action="/SpringWebMvcExam_war/uploadInventory">
    <input type="file" name="file" accept="xlsx" />
    <input type="submit" value="File Upload" />
    
</form>
</body>
</html>
