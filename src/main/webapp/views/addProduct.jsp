<%--
  Created by IntelliJ IDEA.
  User: PhuocNTB
  Date: 6/2/2025
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
  <form:form action="/product/add" method="post" modelAttribute="product" enctype="multipart/form-data">
    Tên sản phẩm: <form:input path="name" /><br/>
    Giá tiền: <form:input path="price" /><br/>
    <input type="file" name="icon" multiple />
    <input type="submit" value="Thêm"/>
  </form:form>
</body>
</html>
