<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library OS - Entry</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <div class="welcome-screen">
        <div class="system-status">System Online // Database Connected</div>
        <h1 class="welcome-title">
            Library <span class="text-accent welcome-title-bold">Management</span> System
        </h1>

        <div class="welcome-buttons">
            <a href="${pageContext.request.contextPath}/libraries" class="btn btn-primary">
                Enter Libraries
            </a>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-outline">
                Browse Books
            </a>
        </div>
    </div>
</div>
</body>
</html>