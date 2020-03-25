<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 17.09.2019
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tm" uri="time" %>
<html>
<head>

    <meta charset="utf-8">

    <title>Order</title>

</head>
<body>

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <div class="order">
        <div class="form-order-container">
            <div class="box-form">
                <div class="image-form">
                    <img src="${meal.image}">
                </div>
                <div class="title-form">
                    <h1 class="title">${meal.name}</h1>
                    <p class="price">$${meal.price}</p>
                    <div class="card-container">
                        <p>
                            ${meal.description}
                        </p>
                    </div>
                </div>
                <div class="payment-form">
                    <form method="post" action="${pageContext.request.contextPath}/controller?command=order&id=${meal.id}">
                        <div class="field">
                            <%--@declare id="time"--%><label for="time">${webContent.getString("meal.time")}</label>
                            <tm:time/>
                        </div>
                        <div class="field">
                            <label for="amount">${webContent.getString("meal.amount")}</label>
                            <input type="number" name="amount" min="1" max="100" maxlength="2" id="amount" required>
                        </div>
                        <div class="field">
                            <label for="payment">${webContent.getString("meal.payment")}</label>
                            <select id="payment" name="payment" required>
                                <option>account</option>
                                <option>cash</option>
                            </select>
                        </div>
                        <div class="field button-order">
                            <input type="hidden" name="price" value="${meal.price}">
                            <label class="price">${webContent.getString("meal.one")} $ - ${meal.price}</label>
                            <input type="submit" name="" value="${webContent.getString("meal.order")}">
                            <p>${error}</p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
