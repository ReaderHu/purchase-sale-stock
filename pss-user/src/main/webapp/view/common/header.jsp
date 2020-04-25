<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/6 0006
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
    <%-- 引入jquery--%>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/static/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>工厂管理系统</h1>

    <div class="publicHeaderR">
        <p><span style="color: #fff21b">${sessionUser.userName}</span> , 欢迎你！</p>
        <a href="${pageContext.request.contextPath}/login/loginout">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
</section>
<script src="${pageContext.request.contextPath}/static/js/time.js"></script>
</body>
</html>
