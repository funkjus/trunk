<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.05.2021
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>GOODS</title>
</head>
<body>
<h2>Goods</h2>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
<%--        <th>color</th>--%>
<%--        <th>size</th>--%>
    </tr>
    <c:forEach var="good" items="${goodList}">
        <tr>
            <td>${good.id}</td>
            <td>${good.name}</td>
            <td>${good.price}</td>
<%--            <td>${good.}</td>--%>
<%--            <td></td>--%>
            <td>
                <a href="/editGood/${good.id}">edit</a>
                <a href="/deleteGood/${good.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/addGood/${traderId}">Add good</a>
</body>
</html>
