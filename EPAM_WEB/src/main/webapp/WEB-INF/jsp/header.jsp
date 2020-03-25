<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 05.09.2019
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.web.entity.enums.UserRole" %>
<html>
<head>
    <meta charset="utf-8">

    <title>EPAM-CAFE</title>

    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Fjalla+One&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="assets/img/logo/favicon.ico">

    <meta name="description" content="Responsive Header Nav">
    <meta name="author" content="Treehouse">
    <meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1">
</head>
<body>

<div class="header">
    <div class="header-left">
        <a href="${pageContext.request.contextPath}/controller?command=main" class="logo">
            <img src="assets/img/logo/logo_white-blue.svg">
        </a>
        <c:if test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.user.role != UserRole.MANAGER}">
                <a href="${pageContext.request.contextPath}/controller?command=menu">
                        ${webContent.getString("menu")}
                </a>
            </c:if>
            <c:if test="${sessionScope.user.role == UserRole.ADMIN}">
                <a href="${pageContext.request.contextPath}/controller?command=users">
                        ${webContent.getString("users")}
                </a>
            </c:if>
            <c:if test="${sessionScope.user.role == UserRole.USER}">
                <a href="${pageContext.request.contextPath}/controller?command=history">
                        ${webContent.getString("history")}
                </a>
            </c:if>
            <c:if test="${sessionScope.user.role == UserRole.MANAGER}">
                <a href="${pageContext.request.contextPath}/controller?command=ordersList">
                        ${webContent.getString("orders")}
                </a>
            </c:if>
        </c:if>
    </div>
    <div class="header-right">
        <a href="${pageContext.request.contextPath}/controller?command=locale&language=ru&country=RU">
            ${webContent.getString("language.ru")}
        </a>
        <a href="${pageContext.request.contextPath}/controller?command=locale&language=en&country=US">
            ${webContent.getString("language.en")}
        </a>
        <c:if test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/controller?command=signIn">
                    ${webContent.getString("join")}
            </a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                    ${webContent.getString("exit")}
            </a>
        </c:if>
    </div>
</div>


</body>
</html>
