<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Books</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h2 style="font-weight: 300;">Book <span style="color: var(--accent)">Collection</span></h2>
        <form action="${pageContext.request.contextPath}/books" method="get" style="display: flex; gap: 10px;">
            <input type="text" name="${AttributesHolder.SEARCH}" placeholder="Find book..." style="margin: 0; width: 200px; padding: 8px 15px;">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Library</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${requestScope[AttributesHolder.BOOKS]}">
            <tr>
                <td>${book.id}</td>
                <td style="color: var(--accent); font-weight: 600;">${book.title}</td>
                <td>${book.author}</td>
                <td style="color: var(--text-secondary);">${book.library.name}</td>
                <td style="display: flex; gap: 10px;">
                    <a href="${pageContext.request.contextPath}/books/edit/${book.id}" class="btn btn-outline">Edit</a>
                    <form action="${pageContext.request.contextPath}/books/delete/${book.id}" method="post" style="margin:0;">
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