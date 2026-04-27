<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Books</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="flex-between">
        <h2 class="page-title">Book <span class="text-accent">Collection</span></h2>
        <form action="${pageContext.request.contextPath}/books" method="get" class="search-form">
            <input type="text" name="${AttributesHolder.SEARCH}" placeholder="Find book..." class="search-input" value="${requestScope[AttributesHolder.SEARCH]}">
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th class="col-id">ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Library</th>
            <th class="col-actions">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${requestScope[AttributesHolder.BOOKS]}">
            <tr>
                <td>${book.id}</td>
                <td class="table-text-bold text-accent">${book.title}</td>
                <td>${book.author}</td>
                <td style="color: var(--text-secondary);">${book.library.name}</td>
                <td class="actions-cell">
                    <a href="${pageContext.request.contextPath}/books/edit/${book.id}" class="btn btn-outline">Edit</a>
                    <form action="${pageContext.request.contextPath}/books/delete/${book.id}" method="post" class="margin-0">
                        <button class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${noOfPages > 1}">
        <div class="pagination">
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <a href="${pageContext.request.contextPath}/books?page=${i}&search=${requestScope[AttributesHolder.SEARCH]}"
                   class="btn ${currentPage == i ? 'btn-primary' : 'btn-outline'}">${i}</a>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>