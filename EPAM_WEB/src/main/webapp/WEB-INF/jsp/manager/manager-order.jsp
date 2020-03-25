<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 23.09.2019
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Orders</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="order-content">
    <div class="content-name">
        <div class="content-number">&nbsp;</div>
        <div class="content-user">${webContent.getString("order.user")}</div>
        <div class="content-meal">${webContent.getString("order.meal")}</div>
        <div class="content-time">${webContent.getString("order.time")}</div>
        <div class="content-amount">${webContent.getString("order.amount")}</div>
        <div class="content-total">${webContent.getString("order.total")}</div>
        <div class="content-status">${webContent.getString("order.status")}</div>
        <div class="content-paid">${webContent.getString("order.paid")}</div>
        <div class="content-cancel">${webContent.getString("order.cancel")}</div>
    </div>

    <c:forEach var="elem" items="${newOrders}" varStatus="status">
        <c:set var="order" scope="request" value="${elem.order}"/>
        <c:set var="user" scope="request" value="${elem.order.user}"/>
        <c:set var="meal" scope="request" value="${elem.meal}"/>
        <div class="content-order">
            <div class="content-number">${status.index + 1}</div>
            <div class="content-user">${user.login}</div>
            <div class="content-meal">${meal.name}</div>
            <div class="content-time">
                <c:if test="${not empty order.time}">
                    ${order.time}
                </c:if>
                <c:if test="${empty order.time}">
                    &nbsp;
                </c:if>
            </div>
            <div class="content-amount">${elem.amount}</div>
            <div class="content-total">${meal.price * elem.amount} $</div>
            <div class="content-status">${order.status}</div>
            <div class="content-paid">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=managerPaid">
                    <input type="hidden" name="id" value="${order.id}">
                    <input type="submit" name="action" value="${webContent.getString("order.paid")}">
                </form>
            </div>
            <div class="content-cancel">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=managerCancel">
                    <input type="hidden" name="order_id" value="${order.id}">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <input type="hidden" name="points" value="${user.points}">
                    <input type="hidden" name="block" value="${user.blocked}">
                    <input type="submit" name="action" value="${webContent.getString("order.cancel")}">
                </form>
            </div>
        </div>
    </c:forEach>

</div>

</body>
</html>
