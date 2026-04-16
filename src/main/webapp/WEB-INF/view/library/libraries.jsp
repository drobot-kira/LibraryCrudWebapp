<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Libraries</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h2 style="font-weight: 300;">Library <span style="color: var(--accent)">Catalog</span></h2>
        <form action="${pageContext.request.contextPath}/libraries" method="get" style="display: flex; gap: 10px;">
            <input type="text" name="${AttributesHolder.SEARCH}" placeholder="Search..." style="margin: 0; width: 200px; padding: 8px 15px;">
            <button type="submit" class="btn btn-primary">Go</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Foundation</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lib" items="${requestScope[AttributesHolder.LIBRARIES]}">
            <tr>
                <td>${lib.id}</td>
                <td style="color: #fff; font-weight: 600;">${lib.name}</td>
                <td>${lib.foundationYear}</td>
                <td style="display: flex; gap: 10px;">
                    <a href="${pageContext.request.contextPath}/libraries/edit/${lib.id}" class="btn btn-outline">Edit</a>
                    <form action="${pageContext.request.contextPath}/libraries/delete/${lib.id}" method="post" style="margin:0;">
                        <button class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>