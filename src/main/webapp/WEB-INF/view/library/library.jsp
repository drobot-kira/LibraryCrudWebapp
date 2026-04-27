<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Manage Library</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="form-card">
        <h2 class="form-title">
            <c:choose>
                <c:when test="${!newMode}">Edit <span class="text-accent">Library</span></c:when>
                <c:otherwise>New <span class="text-accent">Library</span></c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty requestScope[AttributesHolder.ERROR_MESSAGE]}">
            <div class="error-global">
                <span>[ SYSTEM ERROR ]</span> : <c:out value="${requestScope[AttributesHolder.ERROR_MESSAGE]}"/>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}${newMode ? '/libraries/add' : '/libraries/edit'}" method="post">
            <c:if test="${!newMode}">
                <input type="hidden" name="${AttributesHolder.ID}" value="${library.id}">
            </c:if>

            <label class="input-label">LIBRARY NAME</label>
            <input type="text" name="${AttributesHolder.NAME}" value="${library.name}" maxlength="100" required>
            <c:if test="${not empty errors['name']}">
                <div class="field-error"><c:out value="${errors['name']}"/></div>
            </c:if>

            <label class="input-label">FOUNDATION YEAR</label>
            <input type="number" name="${AttributesHolder.FOUNDATION_YEAR}" value="${library.foundationYear}" min="0" max="2026" required>
            <c:if test="${not empty errors['foundation_year']}">
                <div class="field-error"><c:out value="${errors['foundation_year']}"/></div>
            </c:if>

            <button type="submit" class="btn btn-primary btn-full">Save Data</button>
        </form>
    </div>
</div>
</body>
</html>