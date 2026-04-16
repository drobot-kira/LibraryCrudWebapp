<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library OS - Entry</title>
    <%-- Підключаємо твої стилі --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .welcome-screen {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh;
            text-align: center;
        }
        .system-status {
            font-size: 0.7rem;
            color: var(--accent);
            text-transform: uppercase;
            letter-spacing: 3px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="welcome-screen">
        <div class="system-status">System Online // Database Connected</div>
        <h1 style="font-weight: 300; font-size: 3rem; margin-bottom: 40px;">
            Library <span style="color: var(--accent); font-weight: 600;">Management</span> System
        </h1>

        <div style="display: flex; gap: 20px;">
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