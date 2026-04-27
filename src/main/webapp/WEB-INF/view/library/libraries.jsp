<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Libraries</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="flex-between">
        <h2 class="page-title">Library <span class="text-accent">Catalog</span></h2>
        <form action="${pageContext.request.contextPath}/libraries" method="get" class="search-form">
            <input type="text" name="${AttributesHolder.SEARCH}" placeholder="Search..." class="search-input" value="${requestScope[AttributesHolder.SEARCH]}">
            <button type="submit" class="btn btn-primary">Go</button>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th class="col-id">ID</th>
            <th>Name</th>
            <th>Foundation</th>
            <th class="col-actions">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="lib" items="${requestScope[AttributesHolder.LIBRARIES]}">
            <tr>
                <td>${lib.id}</td>
                <td class="table-text-bold">${lib.name}</td>
                <td>${lib.foundationYear}</td>
                <td class="actions-cell">
                    <a href="${pageContext.request.contextPath}/libraries/edit/${lib.id}" class="btn btn-outline">Edit</a>
                    <form action="${pageContext.request.contextPath}/libraries/delete/${lib.id}" method="post" class="margin-0">
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
                <a href="${pageContext.request.contextPath}/libraries?page=${i}&search=${requestScope[AttributesHolder.SEARCH]}"
                   class="btn ${currentPage == i ? 'btn-primary' : 'btn-outline'}">${i}</a>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>