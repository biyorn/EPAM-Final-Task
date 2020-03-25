<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 08.09.2019
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.web.entity.enums.UserRole" %>
<html>
<head>
    <meta charset="UTF-8">

    <title>Users</title>

</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="user-panel">
    <div class="user-card">
        <h1 class="user-title">${webContent.getString("users")}</h1>
        <div>
            <table class="user-table">
                <tr>
                    <th class="user-table-number">&nbsp;</th>
                    <th>${webContent.getString("users.username")}</th>
                    <th>${webContent.getString("users.balance")} $</th>
                    <th>${webContent.getString("users.points")}</th>
                    <th>${webContent.getString("users.block")}</th>
                    <th>${webContent.getString("users.action")}</th>
                </tr>

                <c:forEach var="elem" items="${listUsers}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${elem.login}</td>
                        <td>${elem.balance}</td>
                        <td>${elem.points}</td>
                        <td>
                            <c:if test="${elem.blocked == true}">
                                &#10004;
                            </c:if>
                            <c:if test="${elem.blocked == false}">
                                &nbsp;
                            </c:if>
                        </td>
                        <td>
                            <button id="myBtn"
                                    class="openmodal edit-button">${webContent.getString("users.edit")}</button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>

<c:forEach var="elem" items="${listUsers}" varStatus="status">
    <div id="myModal" class="modal  edit-modal">

        <div class="edit-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>${webContent.getString("edit.user")}</h2>
            </div>
            <div class="edit-body">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=edit&id=${elem.id}">
                    <input type="text" name="name" value="${elem.login}"
                           pattern="[a-zA-Z]+" disabled>
                    <input type="text" name="balance" value="${elem.balance}" pattern="[\d]*[\.]?[\d]{1,2}"
                           maxlength="6" required
                           <c:if test="${elem.role == UserRole.ADMIN || elem.role == UserRole.MANAGER}">disabled</c:if>>
                    <input type="text" name="points" value="${elem.points}" pattern="[\d]{1,2}" maxlength="2" required
                           <c:if test="${elem.role == UserRole.ADMIN || elem.role == UserRole.MANAGER}">disabled</c:if>>
                    <div class="block-content">
                        <label>${webContent.getString("edit.unblock")}</label>
                        <input type="radio" name="blocked" value="unblock"
                               <c:if test="${elem.blocked == false}">checked</c:if> required
                               <c:if test="${elem.role == UserRole.ADMIN || elem.role == UserRole.MANAGER}">disabled</c:if>>
                    </div>
                    <div class="block-content">
                        <label>${webContent.getString("edit.block")}</label>
                        <input type="radio" name="blocked" value="block"
                               <c:if test="${elem.blocked == true}">checked</c:if> required
                               <c:if test="${elem.role == UserRole.ADMIN || elem.role == UserRole.MANAGER}">disabled</c:if>>
                    </div>
                    <input type="submit" value="${webContent.getString("users.edit")}"
                           <c:if test="${elem.role == UserRole.ADMIN || elem.role == UserRole.MANAGER}">disabled</c:if>>
                </form>
            </div>
        </div>

    </div>
</c:forEach>


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
</script>

</body>
</html>
