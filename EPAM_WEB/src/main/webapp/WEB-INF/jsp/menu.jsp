<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 08.09.2019
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.web.entity.enums.UserRole" %>
<%@ taglib prefix="pg" uri="pagination" %>
<html>
<head>
    <meta charset="utf-8">

    <title>Menu</title>

</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<c:if test="${sessionScope.user.role == UserRole.ADMIN}">
    <button id="myBtn" class="meal-button">${webContent.getString("add")}</button>

    <div id="myModal" class="modal">

        <div class="modal-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>${webContent.getString("add.meal")}</h2>
            </div>
            <div class="modal-body">
                <form class="form-order" method="post"
                      action="${pageContext.request.contextPath}/controller?command=newMeal" enctype="multipart/form-data">
                    <input type="file" name="image" class="file" accept="image/*" placeholder="select image" required>
                    <input type="text" name="name"
                           placeholder="${webContent.getString("add.name")}" pattern="[a-zA-Z\s]{5,30}" maxlength="30" required>
                    <input type="text" name="description"
                           placeholder="${webContent.getString("add.description")}" pattern="[\w\s.,]{10,180}"
                           maxlength="180" required>
                    <input type="text" name="price"
                           placeholder="${webContent.getString("add.price")} $" pattern="[\d]*[\.]?[\d]{1,2}"
                           maxlength="8" required>
                    <input type="submit" value="${webContent.getString("add.create")}">
                </form>
            </div>
        </div>

    </div>

    <script>
        var modal = document.getElementById("myModal");
        var btn = document.getElementById("myBtn");
        var span = document.getElementsByClassName("close")[0];

        btn.onclick = function () {
            modal.style.display = "block";
        };
        span.onclick = function () {
            modal.style.display = "none";
        };
        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        };
    </script>
</c:if>

<c:if test="${not empty sessionScope.user && sessionScope.user.role == UserRole.USER}">
    <button id="myBtn" class="balance">${sessionScope.user.balance}$</button>

    <div id="myModal" class="modal">

        <div class="balance-content">
            <div class="balance-header">
                <span class="close">&times;</span>
                <img src="assets/img/logo/card.jpg">
            </div>
            <div class="balance-body">
                <form class="form-balance" method="post"
                      action="${pageContext.request.contextPath}/controller?command=balance">
                    <input type="text" name="card" placeholder="card number" class="card-number" pattern="[\d]{16}"
                           maxlength="16" required>
                    <label class="valid-thru">VALID THRU</label><br/>
                    <span><input type="text" name="mm" placeholder="MM" class="card-mm"
                                 pattern="(0[1-9]|10|11|12)" maxlength="2" required></span>
                    /
                    <span><input type="text" name="yy" placeholder="YY" class="card-yy"
                                 pattern="[2-9][0-9]" maxlength="2" required></span>
                    <span><input type="password" name="cvs" placeholder="CVS" class="cvs" pattern="[\d]{3}" maxlength="3"
                                 required></span><br/>
                    <input type="text" name="amount" placeholder="${webContent.getString("balance.amount")}" class="sum"
                           pattern="[\d]*[\.]?[\d]{1,2}" maxlength="6" required><br/>
                    <input type="submit" value="${webContent.getString("balance.replenish")}" class="replenish">
                </form>
            </div>
        </div>

    </div>
    <script>
        var modal = document.getElementById("myModal");
        var btn = document.getElementById("myBtn");
        var span = document.getElementsByClassName("close")[0];

        btn.onclick = function () {
            modal.style.display = "block";
        };
        span.onclick = function () {
            modal.style.display = "none";
        };
        window.onclick = function (event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        }
    </script>
</c:if>

<div class="menu-grid">

    <c:forEach var="elem" items="${menu}" varStatus="status">
        <div class="card">
            <c:if test="${sessionScope.user.role == UserRole.ADMIN}">
                <div class="delete">
                    <button class="delete-modal openmodal">&times;</button>
                </div>
            </c:if>
            <c:if test="${sessionScope.user.role == UserRole.USER}">
            <a href="${pageContext.request.contextPath}/controller?command=meal&id=${elem.id}">
            </c:if>
                <img src="${elem.image}" alt="meal" height="250px">
                <h1 class="title">${elem.name}</h1>
                <p class="price">$${elem.price}</p>
                <div class="card-container">
                    <p>${elem.description}</p>
                </div>
            <c:if test="${sessionScope.user.role == UserRole.USER}">
            </a>
            </c:if>
        </div>
    </c:forEach>

    <c:if test="${sessionScope.user.role == UserRole.ADMIN}">
        <c:forEach var="elem" items="${menu}" varStatus="status">
            <div class="deleteModal">
                <div class="delete-content">
                    <div class="modal-header">
                        <span class="delete-close">&times;</span>
                        <h2>${webContent.getString("delete.meal")}</h2>
                    </div>
                    <div class="delete-body">
                        <div class="form-delete">
                            <p>${webContent.getString("delete.question")} "${elem.name}"?</p>
                            <a href="${pageContext.request.contextPath}/controller?command=deleteMeal&id=${elem.id}">
                                <input type="submit" name="" value="${webContent.getString("delete.yes")}" class="yes">
                            </a>
                            <a href="${pageContext.request.contextPath}/controller?command=menu&page=${page}">
                                <input type="button" name="" value="${webContent.getString("delete.no")}" class="no">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <script>
            var modals = document.getElementsByClassName('deleteModal');
            var btns = document.getElementsByClassName("openmodal");
            var spans = document.getElementsByClassName("delete-close");
            for (let i = 0; i < btns.length; i++) {
                btns[i].onclick = function () {
                    modals[i].style.display = "block";
                }
            }
            for (let i = 0; i < spans.length; i++) {
                spans[i].onclick = function () {
                    modals[i].style.display = "none";
                };
            }
        </script>
    </c:if>

</div>

<c:if test="${amount > 4}">
    <div class="center">
        <div class="pagination">
            <pg:pagination amount="${amount}" page="${page}"/>
        </div>
    </div>
</c:if>

</body>
</html>
