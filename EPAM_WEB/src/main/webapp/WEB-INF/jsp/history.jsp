<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 20.09.2019
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.web.entity.enums.OrderStatus" %>
<html>
<head>
    <meta charset="utf-8">

    <title>History</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>


<div class="history">
    <div class="history-fields">
        <div class="field-name">
            <p>${webContent.getString("history.name")}</p>
        </div>
        <div class="field-time">
            <p>${webContent.getString("history.time")}</p>
        </div>
        <div class="field-status">
            <p>${webContent.getString("history.status")}</p>
        </div>
        <div class="field-total">
            <p>${webContent.getString("history.total")}</p>
        </div>
        <div class="field-payment">
            <p>${webContent.getString("history.payment")}</p>
        </div>
    </div>
    <c:forEach var="elem" items="${userOrders}" varStatus="status">
        <c:set var="order" scope="request" value="${elem.order}"/>
        <c:set var="meal" scope="request" value="${elem.meal}"/>
        <div class="collapsible history-item">
            <div class="field-name">
                <p>${meal.name}</p>
            </div>
            <div class="field-time">
                <c:if test="${not empty order.time}">
                    <p>${elem.order.time}</p>
                </c:if>
                <c:if test="${empty order.time}">
                    <p>&nbsp;</p>
                </c:if>
            </div>
            <div class="field-status">
                <p>${order.status}</p>
            </div>
            <div class="field-total">
                <p>${meal.price * elem.amount} $</p>
            </div>
            <div class="field-payment">
                <p>${order.payment}</p>
            </div>
        </div>
        <div class="content">
            <div class="content-fields">
                <div class="content-name">
                    <p>&nbsp;</p>
                </div>
                <div class="content-time">
                    <p>${webContent.getString("history.time")}</p>
                </div>
                <div class="content-status">
                    <p>${webContent.getString("history.status")}</p>
                </div>
                <div class="content-amount">
                    <p>${webContent.getString("history.amount")}</p>
                </div>
                <div class="content-total">
                    <p>${webContent.getString("history.total")}</p>
                </div>
                <div class="content-payment">
                    <p>${webContent.getString("history.payment")}</p>
                </div>
            </div>
            <div class="content-items">
                <div class="content-item">
                    <img src="${meal.image}" class="img">
                </div>
                <div class="content-item">
                    <c:if test="${not empty order.time}">
                        <p>${order.time}</p>
                    </c:if>
                    <p>&nbsp;</p>
                </div>
                <div class="content-item">
                    <p>${order.status}</p>
                </div>
                <div class="content-item">
                    <p>${elem.amount}</p>
                </div>
                <div class="content-item">
                    <p>${meal.price * elem.amount} $</p>
                </div>
                <div class="content-item">
                    <p>${order.payment}</p>
                </div>

                <c:if test="${order.status == OrderStatus.NEW}">
                    <form method="post"
                          action="${pageContext.request.contextPath}/controller?command=userCancel&id=${order.id}">
                        <input type="hidden" name="page" value="history">
                        <input type="submit" class="content-action button-cancel" value="&times;">
                    </form>
                </c:if>
                <c:if test="${order.status == OrderStatus.PAID}">
                    <button id="myBtn"
                            class="openmodal review-button">${webContent.getString("history.feedback")}</button>
                </c:if>
            </div>
        </div>

        <div id="myModal" class="modal">

            <div class="modal-content review">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>${webContent.getString("history.review")}</h2>
                </div>
                <div class="modal-body review-body">
                    <form method="post"
                          action="${pageContext.request.contextPath}/controller?command=feedback&id=${elem.order.id}">
                        <textarea maxlength="255" rows="10" cols="40" name="review">${elem.order.review}</textarea>
                        <button type="submit" name="">${webContent.getString("history.feedback")}</button>
                    </form>
                </div>
            </div>

        </div>

    </c:forEach>
</div>


<script>
    var modals = document.getElementsByClassName('modal');
    var btns = document.getElementsByClassName("openmodal");
    var spans = document.getElementsByClassName("close");
    for (let i = 0; i < btns.length; i++) {
        btns[i].onclick = function () {
            modals[i].style.display = "block";
        }
    }
    for (let i = 0; i < spans.length; i++) {
        spans[i].onclick = function () {
            modals[i].style.display = "none";
        }
    }

    let coll = document.getElementsByClassName('collapsible');
    for (let i = 0; i < coll.length; i++) {
        coll[i].addEventListener('click', function () {
            this.classList.toggle('active');
            let content = this.nextElementSibling;
            if (content.style.maxHeight) {
                content.style.maxHeight = null;
            } else {
                content.style.maxHeight = content.scrollHeight + 'px';
            }
        })
    }
</script>

</body>
</html>
