<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.08.2019
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="content-error">
    <div class="error">
        <div class="error-header">
            <p>ERROR</p>
        </div>
        <div class="error-message">
            ${error}
        </div>
    </div>
</div>

</body>
</html>
