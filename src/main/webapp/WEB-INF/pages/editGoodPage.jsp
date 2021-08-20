<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.05.2021
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty good.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty good.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty good.name}">
    <c:url value="/addGood/" var="var"/>
</c:if>
<c:if test="${!empty good.name}">
    <c:url value="/editGood/" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <input type="hidden" name="traderId" value="${traderId}">
    <c:if test="${!empty good.name}">
        <input type="hidden" name="id" value="${good.id}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="price">Price</label>
    <input type="number" name="price" id="price">
    <label for="url">Url</label>
    <input type="text" name="url" id="url">
    <c:if test="${empty good.name}">
        <input type="submit" value="Add Good">
    </c:if>
    <c:if test="${!empty good.name}">
        <input type="submit" value="Edit Good">
    </c:if>
</form>
</body>
</html>
