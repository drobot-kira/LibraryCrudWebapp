<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Manage Book</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="form-card">
        <h2 class="form-title">
            <c:choose>
                <c:when test="${!newMode}">Edit <span class="text-accent">Book</span></c:when>
                <c:otherwise>New <span class="text-accent">Book</span></c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty requestScope[AttributesHolder.ERROR_MESSAGE]}">
            <div class="error-global">
                <span>[ SYSTEM ERROR ]</span> : <c:out value="${requestScope[AttributesHolder.ERROR_MESSAGE]}"/>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}${newMode ? '/books/add' : '/books/edit'}" method="post">
            <c:if test="${!newMode}">
                <input type="hidden" name="${AttributesHolder.ID}" value="${book.id}">
            </c:if>

            <label class="input-label">BOOK TITLE</label>
            <input type="text" name="${AttributesHolder.TITLE}" value="${book.title}" maxlength="200" required>
            <c:if test="${not empty errors['title']}">
                <div class="field-error"><c:out value="${errors['title']}"/></div>
            </c:if>

            <label class="input-label">AUTHOR NAME</label>
            <input type="text" name="${AttributesHolder.AUTHOR}" value="${book.author}" maxlength="100" required>
            <c:if test="${not empty errors['author']}">
                <div class="field-error"><c:out value="${errors['author']}"/></div>
            </c:if>

            <label class="input-label">ASSIGN TO LIBRARY</label>
            <select name="${AttributesHolder.LIBRARY_ID}">
                <c:forEach var="lib" items="${libraries}">
                    <option value="${lib.id}" ${book.library.id == lib.id ? 'selected' : ''}>
                            ${lib.name}
                    </option>
                </c:forEach>
            </select>
            <c:if test="${not empty errors['library_id']}">
                <div class="field-error"><c:out value="${errors['library_id']}"/></div>
            </c:if>

            <button type="submit" class="btn btn-primary btn-full">Save Changes</button>
        </form>
    </div>
</div>
</body>
</html>