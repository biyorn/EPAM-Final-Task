<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">

    <title>Login</title>

</head>
<body>

    <jsp:include page="WEB-INF/jsp/header.jsp"/>

    <div class="main-form">
        <form class="form-login" action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <h2 class="login-text">
                ${webContent.getString("join.login")}
            </h2>
            <input type="text" name="login" placeholder="${webContent.getString("join.login")}" pattern="[a-zA-Z]{4,8}" required>
            <input type="password" name="pass" placeholder="${webContent.getString("join.password")}" pattern="[\w]{3,8}" required>
            <input type="submit" value="${webContent.getString("button.login")}">
            <p>${error}</p>
        </form>
    </div>



</body>
</html>