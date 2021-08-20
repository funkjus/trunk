<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25.05.2021
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TRADERS</title>
</head>
<body>

<h2>Traders</h2>
<table>
    <tr>
        <th>id</th>
        <th>url</th>
        <th>name</th>
        <th>adress</th>
    </tr>
    <c:forEach var="trader" items="${traderList}">
        <tr>
            <td>${trader.id}</td>
            <td>${trader.url}</td>
            <td>${trader.name}</td>
            <td>${trader.adress}</td>
            <td>
                <a href="/edit/${trader.id}">edit</a>
                <a href="/delete/${trader.id}">delete</a>
                <a href="/goods/${trader.id}">goods</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new Trader</a>
</body>
</html>
