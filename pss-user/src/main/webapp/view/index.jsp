<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/5 0005
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工厂管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>
<!--头部-->
<jsp:include page="/view/common/header.jsp" flush="true"/>
<!--主体内容-->
<section class="publicMian">
    <jsp:include page="/view/common/menu.jsp" />
    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath}/static/img/clock.jpg" alt=""/>
        <div class="wFont">
            <h2>${sessionUser.userName}</h2>
            <p>欢迎来到工厂管理系统!</p>
            <span id="hours">系统用户总计${userCount}人</span>
        </div>
    </div>
</section>
<jsp:include page="/view/common/footer.jsp"/>
</div>
</body>
</html>
