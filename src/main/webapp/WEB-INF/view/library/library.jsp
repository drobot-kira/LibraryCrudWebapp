<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder" %>
<html>
<head><title>Manage Library</title></head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="form-card">
        <h2 style="margin-bottom: 30px; font-weight: 300;">
            <c:choose>
                <c:when test="${!newMode}">Edit <span style="color: var(--accent)">Library</span></c:when>
                <c:otherwise>New <span style="color: var(--accent)">Library</span></c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty requestScope[AttributesHolder.ERROR_MESSAGE]}">
            <div style="
        background: rgba(255, 77, 77, 0.1);
        border: 1px solid #ff4d4d;
        color: #ff4d4d;
        padding: 15px;
        border-radius: 12px;
        margin-bottom: 25px;
        font-size: 14px;
        letter-spacing: 1px;
        text-transform: uppercase;
        box-shadow: 0 0 10px rgba(255, 77, 77, 0.2);
    ">
                <span style="font-weight: bold;">[ SYSTEM ERROR ]</span> : <c:out value="${requestScope[AttributesHolder.ERROR_MESSAGE]}"/>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}${newMode ? '/libraries/add' : '/libraries/edit'}" method="post">
            <c:if test="${!newMode}">
                <input type="hidden" name="${AttributesHolder.ID}" value="${library.id}">
            </c:if>

            <label style="font-size: 12px; color: var(--text-secondary);">LIBRARY NAME</label>
            <input type="text" name="${AttributesHolder.NAME}" value="${library.name}" required>

            <label style="font-size: 12px; color: var(--text-secondary);">FOUNDATION YEAR</label>
            <input type="number" name="${AttributesHolder.FOUNDATION_YEAR}" value="${library.foundationYear}" required>

            <button type="submit" class="btn btn-primary" style="width: 100%; margin-top: 20px;">Save Data</button>
        </form>
    </div>
</div>
</body>
</html>