<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 - LOST IN THE VOID</title>
</head>
<body style="overflow: hidden;">
<%@include file="../header.jsp"%>

<div class="error-page-container">
    <h1 class="error-404-title">404</h1>
    <p class="error-404-text">Data Corrupted: This record does not exist in our reality.</p>
    <a href="${pageContext.request.contextPath}/libraries" class="btn btn-primary">
        REBOOT SYSTEM
    </a>
</div>
</body>
</html>