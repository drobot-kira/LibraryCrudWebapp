<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<nav>
    <a href="${pageContext.request.contextPath}/libraries" class="nav-link">Libraries</a>
    <a href="${pageContext.request.contextPath}/libraries/add" class="nav-link">Add Library</a>
    <a href="${pageContext.request.contextPath}/books" class="nav-link">Books</a>
    <a href="${pageContext.request.contextPath}/books/add" class="nav-link">Add Book</a>
</nav>