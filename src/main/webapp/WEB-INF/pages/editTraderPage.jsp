<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.05.2021
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty trader.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty trader.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty trader.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty trader.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty trader.name}">
        <input type="hidden" name="id" value="${trader.id}">
    </c:if>
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="url">Url</label>
    <input type="text" name="url" id="url">
    <label for="adress">Adress</label>
    <input type="text" name="adress" id="adress">
    <c:if test="${!empty trader.name}">
        <input type="submit" value="Edit trader">
    </c:if>
    <c:if test="${empty trader.name}">
        <input type="submit" value="Add trader">
    </c:if>
</form>
</body>
</html>

